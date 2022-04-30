package com.littlecow.blog.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.littlecow.blog.Contants;
import com.littlecow.blog.entity.Blog;
import com.littlecow.blog.entity.Tag;
import com.littlecow.blog.entity.Type;
import com.littlecow.blog.entity.User;
import com.littlecow.blog.exception.NotFoundException;
import com.littlecow.blog.service.BlogsService;
import com.littlecow.blog.service.TagsService;
import com.littlecow.blog.service.TypesService;
import jdk.nashorn.internal.objects.annotations.Getter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/**
 * create by liujiann @2022.4.25
 *
 * */
@Controller
@RequestMapping("/admin")
public class BlogsController {
    @Resource
    private BlogsService blogsService;

    @Resource
    private TypesService typesService;

    @Resource
    private TagsService tagsService;

    @RequestMapping("/blogs")
    @SuppressWarnings("all")
    public String toBlogs(@RequestParam(name="pagenum",defaultValue = "1")int pagenum, Model model){
        PageHelper.startPage(pagenum, Contants.PAGE_SIZE);
        List<Blog> blogList = blogsService.getBlogList();
        for(Blog blog:blogList){
            Type type = typesService.getTypeById(blog.getTypeId());
            if(type == null){
                type = new Type();
                type.setName("");
            }
            blog.setType(type);
        }
        PageInfo<Blog> pageInfo = new PageInfo<>(blogList);
        model.addAttribute(Contants.PAGE_INFO,pageInfo);
        List<Type> types = typesService.getTypeList();
        model.addAttribute("types",types);
        return "admin/blogs";
    }

    @RequestMapping("/blogs/{id}/view")
    public String view(Model model, @PathVariable Long id){
        Blog blog = blogsService.getBlogById(id);
        if(blog == null){
            throw new NotFoundException("博客不存在！");
        }
        Type type = typesService.getTypeById(blog.getTypeId());
        blog.setType(type);
        blog.setTypeId(type.getId());
        List<Tag> tags = tagsService.getTagList();
        List<Type> types = typesService.getTypeList();
        model.addAttribute("tags",tags);
        model.addAttribute("types",types);
        model.addAttribute(Contants.BLOG,blog);
        return "admin/blogs-view";
    }

    @RequestMapping("/blogs/input")
    public String toAddPage(Model model){
        Blog blog = new Blog();
        //flag默认给选中
        blog.setFlag("原创");
        //复选框给它默认选中
        blog.setRecommend(true);
        blog.setShareStatement(true);
        blog.setCommentabled(true);
        blog.setAppreciation(true);
       //给定一个默认初始首图,这样写的时候就可以不用每次单独找图片
        blog.setFirstPicture(Contants.DEFAULT_FIRST_PICTURE_URL);
        List<Tag> tags = tagsService.getTagList();
        List<Type> types = typesService.getTypeList();

        //渲染页面的必要信息
        model.addAttribute(Contants.BLOG,blog);
        model.addAttribute("tags",tags);
        model.addAttribute("types",types);
        return "admin/blogs-input";
    }

    @PostMapping("/blogs")
    public String saveAdd(@RequestParam String flag, Blog blog, RedirectAttributes attributes, HttpSession session){
        blog.setFlag(flag);
        blog.setTypeId(blog.getType().getId());
        User user = (User)session.getAttribute(Contants.USER_SESSION);
        blog.setUserId(user.getId());
        boolean isOk = blogsService.addBlog(blog);
        if(isOk){
            attributes.addFlashAttribute(Contants.MESSAGE,"添加成功！");
            return "redirect:/admin/blogs";
        }else{
            attributes.addFlashAttribute(Contants.MESSAGE,"添加失败！");
            return "redirect:/admin/blogs/input";
        }
    }

    @RequestMapping("/blogs/{id}/delete")
    public String deleteById(@PathVariable Long id,RedirectAttributes attributes){
        boolean isOk = blogsService.deleteBlogById(id);
        if(isOk){
            attributes.addFlashAttribute(Contants.MESSAGE,"删除成功！");
        }else{
            attributes.addFlashAttribute(Contants.MESSAGE,"删除成功！");
        }
        return "redirect:/admin/blogs";
    }

    @RequestMapping("/blogs/{id}/input")
    public String toEditInput(@PathVariable Long id, Model model){
        Blog blog = blogsService.getBlogById(id);
        List<Type> types = typesService.getTypeList();
        List<Tag> tags = tagsService.getTagList();
        blog.setId(id);
        Type type = typesService.getTypeById(blog.getTypeId());
        blog.setType(type);
        blog.setTypeId(type.getId());
        model.addAttribute(Contants.BLOG,blog);
        model.addAttribute("types",types);
        model.addAttribute("tags",tags);
        return "admin/blogs-input";
    }

    @PostMapping("/blogs/{id}")
    public String saveEditBlog(@PathVariable Long id,RedirectAttributes attributes,Blog blog){
        Blog blog1 = blogsService.getBlogById(id);
        if(blog1.equals(blog)){
            attributes.addFlashAttribute(Contants.MESSAGE,"修改成功！");
            return "redirect:/admin/blogs";
        }
        blog.setId(id);
        boolean isOk = blogsService.updateBlog(blog);
        if(!isOk){
            attributes.addFlashAttribute(Contants.MESSAGE,"修改失败!");
        }else {
            attributes.addFlashAttribute(Contants.MESSAGE, "修改成功！");
        }
        return "redirect:/admin/blogs";
    }

    @PostMapping("/blogs/search")
    @SuppressWarnings("all")
    public String searchByCondition(Blog blog, Model model,
                                    @RequestParam(required = false,defaultValue = "1") int pagenum){
        PageHelper.startPage(pagenum,Contants.PAGE_SIZE);
        List<Blog> blogList = blogsService.getBlogByCondition(blog);
        //为每个博客设置type否则前端解析错误，此处也可以在dao层通过连表查询得到
        for(Blog blog1:blogList){
            Type type = typesService.getTypeById(blog1.getTypeId());
            if(type == null){
                type = new Type();
                type.setName("");
            }
            blog1.setType(type);
        }
        PageInfo<Blog> pageInfo = new PageInfo<>(blogList);
        model.addAttribute(Contants.PAGE_INFO,pageInfo);
        List<Type> types = typesService.getTypeList();
        model.addAttribute("types",types);
        model.addAttribute(Contants.MESSAGE,"查询成功！");
        return "admin/blogs::blogList";  //实现局部刷新，只刷新表单部分
    }


}
