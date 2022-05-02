package com.littlecow.blog.controller.front_controller;

import com.littlecow.blog.Contants;
import com.littlecow.blog.entity.*;
import com.littlecow.blog.service.BlogsService;
import com.littlecow.blog.service.TagsService;
import com.littlecow.blog.service.UserLoginService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Controller
public class BlogController {

    @Resource
    private BlogsService blogsService;

    @Resource
    private UserLoginService userLoginService;

    @Resource
    private TagsService tagsService;


    @RequestMapping("/blog/{id}")
    public String blogDetail(@PathVariable Long id, Model model){
        blogsService.updateViews(blogsService.getBlogById(id));  //每一个查看请求将浏览次数加一
        Blog blog = blogsService.getBlogAndConvert(id);  //获取博客并将内容转化为html
        blog.setId(id);
        User user = userLoginService.getUserById(blog.getUserId());
        blog.setUser(user);

        String tagIds = blog.getTagIds();
        List<Tag> tags = new ArrayList<>();
        if(tagIds != null && !"".equals(tagIds)){
            tags = tagsService.getTagByIds(tagIds);
        }
        blog.setTags(tags);
        List<Comment> comments = new ArrayList<>();
        model.addAttribute("comments",comments);
        model.addAttribute(Contants.BLOG,blog);
        return "blog";
    }
}
