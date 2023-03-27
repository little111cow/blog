package com.littlecow.blog.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.littlecow.blog.Contants;
import com.littlecow.blog.controller.front_controller.CommenControllerUtils;
import com.littlecow.blog.entity.Blog;
import com.littlecow.blog.entity.Tag;
import com.littlecow.blog.service.BlogsService;
import com.littlecow.blog.service.TagsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import java.util.List;
/**
 * create by liujiann @2022.4.24
 *
 * */
@Controller
@RequestMapping("/admin")
public class TagsController {
    @Resource
    private TagsService tagsService;

    @Resource
    private BlogsService blogsService;

    @Resource
    private CommenControllerUtils controllerUtils;


    @RequestMapping("/tags")
    public String toTagsPage(@RequestParam(defaultValue = "1",name = "pagenum") int pagenum, Model model){
        PageHelper.startPage(pagenum, Contants.PAGE_SIZE);
        List<Tag> list = tagsService.getTagList();
        PageInfo<Tag> pageInfo = new PageInfo<>(list);
        model.addAttribute(Contants.PAGE_INFO,pageInfo);
        return "admin/tags";
    }

    @RequestMapping("/tags/input")
    public String toAddPage(Model model){
        model.addAttribute(Contants.TAG,new Tag());
        return "admin/tags-input";
    }

    @PostMapping("/tags")
    public String postTag(Tag tag, RedirectAttributes attributes) {
        Tag tag1 = tagsService.getTagByName(tag.getName());
        if(tag1 != null){
            attributes.addFlashAttribute(Contants.MESSAGE,"不能添加重复的标签！");
            return "redirect:/admin/tags/input";
        }
        boolean isOk = tagsService.addTag(tag);
        if(isOk){
            attributes.addFlashAttribute(Contants.MESSAGE,"添加成功！");
            return "redirect:/admin/tags";
        }else {
            attributes.addFlashAttribute(Contants.MESSAGE,"添加失败！");
            return "redirect:/admin/tags/input";
        }
    }

    @RequestMapping("/tags/{id}/delete")
    public String deleteTagById(@PathVariable Long id, RedirectAttributes attributes){
        List<Blog> blogs = blogsService.getBlogList();
        int blogNums = controllerUtils.getBlogsByTagId(id, blogs).size();
        if (blogNums > 0) {
            attributes.addFlashAttribute(Contants.MESSAGE,
                    "标签“" + tagsService.getTagById(id).getName() + "”存在博客，不能删除！");
            return "redirect:/admin/tags";
        }
        boolean deleteOk = tagsService.deleteTagById(id);
        if(deleteOk){
            attributes.addFlashAttribute(Contants.MESSAGE,"删除成功！");
            return "redirect:/admin/tags";
        }else{
            attributes.addFlashAttribute(Contants.MESSAGE,"删除失败！");
            return "redirect:/admin/tags";
        }
    }

    @RequestMapping("/tags/{id}/input")
    public String toEditPage(@PathVariable Long id,Model model){
        Tag tag = tagsService.getTagById(id);
        model.addAttribute(Contants.TAG,tag);
        return "admin/tags-input";
    }

    @PostMapping("/tags/{id}")
    public String editTagSave(@PathVariable Long id, Tag tag,RedirectAttributes attributes){
        Tag tag1 = tagsService.getTagByName(tag.getName());
        if(tag1 != null){
            attributes.addFlashAttribute(Contants.MESSAGE,"不能添加重复的标签！");
            return  "redirect:/admin/tags/input";
        }
        boolean editOk = tagsService.editTag(tag,id);
        if(editOk){
            attributes.addFlashAttribute(Contants.MESSAGE,"修改成功！");
            return "redirect:/admin/tags";
        }else{
            attributes.addFlashAttribute(Contants.MESSAGE,"修改失败！");
            return "redirect:/admin/tags/input";
        }
    }
}
