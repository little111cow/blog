package com.littlecow.blog.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.littlecow.blog.Contants;
import com.littlecow.blog.entity.Comment;
import com.littlecow.blog.service.CommentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class CommentController {
    @Resource
    private CommentService commentService;

    @RequestMapping("/message")
    public String toMessage(@RequestParam(defaultValue = "1") int pagenum, Model model){
        PageHelper.startPage(pagenum, Contants.PAGE_SIZE);
        List<Comment> comments = commentService.getMessageList();
        PageInfo<Comment> pageInfo = new PageInfo<>(comments);
        model.addAttribute(Contants.PAGE_INFO, pageInfo);
        return "admin/message";
    }

    @RequestMapping("/message/{id}/delete")
    public String deleteComment(@PathVariable("id")Long id, RedirectAttributes attributes){
        boolean isOk = commentService.deleteCommentById(id);
        if(isOk) {
            attributes.addFlashAttribute(Contants.MESSAGE, "删除成功！");
        }else{
            attributes.addFlashAttribute(Contants.MESSAGE,"删除失败!");
        }
        return "redirect:/admin/message";
    }
}
