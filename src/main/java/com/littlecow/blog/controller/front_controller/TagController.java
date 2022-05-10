package com.littlecow.blog.controller.front_controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.littlecow.blog.Contants;
import com.littlecow.blog.entity.Blog;
import com.littlecow.blog.entity.Tag;
import com.littlecow.blog.service.BlogsService;
import com.littlecow.blog.service.TagsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * create by liujian 2022.5.9
 * */
@Controller
public class TagController {
    @Resource
    private BlogsService blogsService;

    @Resource
    private TagsService tagsService;

    @Resource
    private CommenControllerUtils controllerUtils;

    @RequestMapping("/tags/{activeTagId}")
    public String toTags(@RequestParam(defaultValue = "1") int pagenum, Model model,
                         @PathVariable Long activeTagId){
        List<Blog> blogList = new ArrayList<>(); //避免为null时渲染错误
        List<Blog> allBlogs = blogsService.getBlogList();
        if(activeTagId == -1L) {  //若目前活动标签为-1，默认将所有tag和博客列表查询出来
            PageHelper.startPage(pagenum, Contants.PAGE_SIZE);  //跟着后边一个select语句会被分页
            blogList = blogsService.getBlogList();
        }else{
            //通过tagid查询对应的所有博客
            PageHelper.startPage(pagenum, Contants.PAGE_SIZE);  //跟着后边一个select语句会被分页
            //此处有问题，需要用select语句实现，否则默认是对blogsService.getBlogList()分页，结果不正确
            blogList = controllerUtils.getBlogsByTagId(activeTagId,allBlogs);
        }
        PageInfo<Blog> pageInfo = new PageInfo<>( controllerUtils.setBlogTypeTagUser(blogList));

        List<Tag> tags = tagsService.getTagList();
        for (Tag tag : tags) {
            //通过tagid获取到对应的所有博客
            List<Blog> blogs = controllerUtils.getBlogsByTagId(tag.getId(), allBlogs);
            tag.setBlogs(blogs);
        }
        model.addAttribute(Contants.PAGE_INFO, pageInfo);
        model.addAttribute("tags", tags);
        return "tags";
    }

}
