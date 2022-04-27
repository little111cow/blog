package com.littlecow.blog.controller.front_controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.littlecow.blog.Contants;
import com.littlecow.blog.entity.Blog;
import com.littlecow.blog.entity.Tag;
import com.littlecow.blog.entity.Type;
import com.littlecow.blog.entity.User;
import com.littlecow.blog.service.BlogsService;
import com.littlecow.blog.service.TagsService;
import com.littlecow.blog.service.TypesService;
import com.littlecow.blog.service.UserLoginService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

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
    private UserLoginService userLoginService;

    @RequestMapping("/index")
    public String index(@RequestParam(defaultValue = "1") int pagenum, Model model){
        PageHelper.startPage(pagenum, Contants.PAGE_SIZE);
        List<Blog> blogList = blogsService.getBlogList();
        for(Blog blog:blogList){
            User user = userLoginService.getUserById(blog.getUserId());
            Type t = typesService.getTypeById(blog.getTypeId());
            blog.setUser(user);
            blog.setType(t);
        }
        PageInfo<Blog> pageInfo = new PageInfo<>(blogList);
        model.addAttribute(Contants.PAGE_INFO,pageInfo);

        List<Tag> tags = tagsService.getTagList();
        for(Tag tag:tags){
            List<Blog> blogs = new ArrayList<>();  //测试
            tag.setBlogs(blogs);
        }
        model.addAttribute("tags",tags);

        List<Type> types = typesService.getTypeList();
        for(Type type:types){
            List<Blog> blogs = blogsService.getBlogsByTypeId(type.getId());
            type.setBlogs(blogs);
        }
        model.addAttribute("types",types);

        List<Blog> recommendBlogs = blogsService.getBlogsByRecommendFlag(true,Contants.RECOMMEND_NUMS);
        model.addAttribute("recommendBlogs",recommendBlogs);
        return "index";
    }

    @RequestMapping("/about")
    public String about(){
        return "about";
    }
}
