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
import com.littlecow.blog.util.RandomAvatarUtils;
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
            throw new NotFoundException("??????????????????");
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
        //flag???????????????
        blog.setFlag("??????");
        //???????????????????????????
        blog.setRecommend(true);
        blog.setShareStatement(true);
        blog.setCommentabled(true);
        blog.setAppreciation(true);
       //??????????????????????????????,?????????????????????????????????????????????????????????????????????????????????????????????
        blog.setFirstPicture(RandomAvatarUtils.RandPicture());
        List<Tag> tags = tagsService.getTagList();
        List<Type> types = typesService.getTypeList();

        //???????????????????????????
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
            attributes.addFlashAttribute(Contants.MESSAGE,"???????????????");
            return "redirect:/admin/blogs";
        }else{
            attributes.addFlashAttribute(Contants.MESSAGE,"???????????????");
            return "redirect:/admin/blogs/input";
        }
    }

    @RequestMapping("/blogs/{id}/delete")
    public String deleteById(@PathVariable Long id,RedirectAttributes attributes){
        boolean isOk = blogsService.deleteBlogById(id);
        if(isOk){
            attributes.addFlashAttribute(Contants.MESSAGE,"???????????????");
        }else{
            attributes.addFlashAttribute(Contants.MESSAGE,"???????????????");
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
            attributes.addFlashAttribute(Contants.MESSAGE,"???????????????");
            return "redirect:/admin/blogs";
        }
        blog.setId(id);
        boolean isOk = blogsService.updateBlog(blog);
        if(!isOk){
            attributes.addFlashAttribute(Contants.MESSAGE,"????????????!");
        }else {
            attributes.addFlashAttribute(Contants.MESSAGE, "???????????????");
        }
        return "redirect:/admin/blogs";
    }

    @PostMapping("/blogs/search")
    @SuppressWarnings("all")
    public String searchByCondition(Blog blog, Model model,
                                    @RequestParam(required = false,defaultValue = "1") int pagenum){
        PageHelper.startPage(pagenum,Contants.PAGE_SIZE);
        List<Blog> blogList = blogsService.getBlogByCondition(blog);
        //?????????????????????type?????????????????????????????????????????????dao???????????????????????????
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
        model.addAttribute(Contants.MESSAGE,"???????????????");
        return "admin/blogs::blogList";  //??????????????????????????????????????????
    }


}
