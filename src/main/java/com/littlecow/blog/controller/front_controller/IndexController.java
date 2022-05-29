package com.littlecow.blog.controller.front_controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.littlecow.blog.Contants;
import com.littlecow.blog.entity.Blog;
import com.littlecow.blog.entity.Tag;
import com.littlecow.blog.entity.Type;
import com.littlecow.blog.service.BlogsService;
import com.littlecow.blog.service.TagsService;
import com.littlecow.blog.service.TypesService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;

/**
 * create by liujian 2022.4.27
 * */

@Controller
public class IndexController {
    @Resource
    private BlogsService blogsService;

    @Resource
    private TagsService tagsService;

    @Resource
    private TypesService typesService;

    @Resource
    private CommenControllerUtils controllerUtils;

    @RequestMapping({"/index","/"})
    public String index(@RequestParam(defaultValue = "1") int pagenum, Model model){
        PageHelper.startPage(pagenum, Contants.PAGE_SIZE);  //跟着后边一个select语句会被分页
        List<Blog> blogList = blogsService.getBlogListPublished();  //此处需要分隔发布状态和草稿状态的博客

        PageInfo<Blog> pageInfo = new PageInfo<>(controllerUtils.setBlogTypeTagUser(blogList));
        model.addAttribute(Contants.PAGE_INFO,pageInfo);

        List<Tag> tags = tagsService.getTagListLimit(Contants.RECOMMEND_NUMS);
        List<Blog> allBlogs = blogsService.getBlogListPublished();  //此处需要分隔发布状态和草稿状态的博客
        for(Tag tag:tags){
            //通过tagid获取到对应的所有博客
            List<Blog> blogs = controllerUtils.getBlogsByTagId(tag.getId(),allBlogs);
            tag.setBlogs(blogs);
        }
        model.addAttribute("tags",tags);

        List<Type> types = typesService.getTypeListLimit(Contants.RECOMMEND_NUMS);
        for(Type type:types){
            List<Blog> blogs = blogsService.getBlogsByTypeId(type.getId()); //此处需要分隔发布状态和草稿状态的博客
            type.setBlogs(blogs);
        }
        model.addAttribute("types",types);

        List<Blog> recommendBlogs = blogsService.getBlogsByRecommendFlag(true,Contants.RECOMMEND_NUMS); //此处需要分隔发布状态和草稿状态的博客
        model.addAttribute("recommendBlogs",recommendBlogs);
        return "index";
    }

    @RequestMapping("/about")
    public String about(){
        return "about";
    }

    @RequestMapping("/archives")
    public String archives(Model model){
        List<Blog> blogList = blogsService.getBlogListPublished();
        int blogCount = blogList.size();
        Map<Integer,Set<Blog>> archiveMap = controllerUtils.archivesByUpdateYear(blogList);
        model.addAttribute("archiveMap",archiveMap);
        model.addAttribute("blogCount",blogCount);
        return "archives";
    }

    @RequestMapping(value = "/search",method = {RequestMethod.GET,RequestMethod.POST})
    public String search(@RequestParam String query,@RequestParam(defaultValue = "1") int pagenum,Model model){
        if(query == null || "".equals(query)){ //空参校验,返回空数据页面
           PageInfo<Blog> pageInfo = new PageInfo<>();
           model.addAttribute(Contants.PAGE_INFO,pageInfo);
           return "search";
        }
        PageHelper.startPage(pagenum,Contants.PAGE_SIZE);
        List<Blog> blogList = blogsService.searchGlobal(query);  //此处需要分隔发布状态和草稿状态的博客

        PageInfo<Blog> pageInfo = new PageInfo<>(controllerUtils.setBlogTypeTagUser(blogList));
        if(blogList.size() == 0){pageInfo.setPageNum(0);}  //空数据时将默认pagenum =1置0
        model.addAttribute(Contants.PAGE_INFO,pageInfo);
        model.addAttribute("query",query);  //回显查询条件
        return "search";
    }
}
