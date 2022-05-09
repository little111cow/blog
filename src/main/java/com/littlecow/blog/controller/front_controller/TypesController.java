package com.littlecow.blog.controller.front_controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.littlecow.blog.Contants;
import com.littlecow.blog.entity.Blog;
import com.littlecow.blog.entity.Type;
import com.littlecow.blog.entity.User;
import com.littlecow.blog.service.BlogsService;
import com.littlecow.blog.service.TypesService;
import com.littlecow.blog.service.UserLoginService;
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
public class TypesController {
    @Resource
    private BlogsService blogsService;

    @Resource
    private TypesService typesService;

    @Resource
    private UserLoginService userLoginService;

    @RequestMapping("/types/{activeTypeId}")
    @SuppressWarnings("all")
    public String toTypes(@RequestParam(defaultValue = "1") int pagenum, Model model,
                          @PathVariable Long activeTypeId){
        List<Blog> blogList = new ArrayList<>();
        if(activeTypeId == -1L) {  //若目前活动类型为-1，默认将所有type和博客列表查询出来
            PageHelper.startPage(pagenum, Contants.PAGE_SIZE);  //跟着后边一个select语句会被分页
            blogList = blogsService.getBlogList();
        }else{
            //通过typeid查询对应的所有博客
            PageHelper.startPage(pagenum, Contants.PAGE_SIZE);
            blogList = blogsService.getBlogsByTypeId(activeTypeId);
        }
        for (Blog blog : blogList) {
            User user = userLoginService.getUserById(blog.getUserId());
            Type t = typesService.getTypeById(blog.getTypeId());
            blog.setUser(user);
            blog.setType(t);
        }
        PageInfo<Blog> pageInfo = new PageInfo<>(blogList);

        List<Type> types = typesService.getTypeList();
        for (Type type : types) {
            //通过typeid获取到对应的所有博客
            List<Blog> blogs = blogsService.getBlogsByTypeId(type.getId());
            type.setBlogs(blogs);
        }
        model.addAttribute("types", types);
        model.addAttribute(Contants.PAGE_INFO, pageInfo);
        return "types";
    }

}
