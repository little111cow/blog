package com.littlecow.blog.controller.front_controller;

import com.littlecow.blog.Contants;
import com.littlecow.blog.entity.Comment;
import com.littlecow.blog.entity.User;
import com.littlecow.blog.service.BlogsService;
import com.littlecow.blog.service.CommentService;
import com.littlecow.blog.util.RandomAvatarUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
public class CommentsController {
    @Resource
    private CommentService commentService;

    @Resource
    private BlogsService blogsService;

//    @Value("${comment.avatar}")
//    private String avatar;

    @GetMapping("/comments/{blogId}")  //展示留言
    public String comments(@PathVariable Long blogId, Model model){
        model.addAttribute("comments", commentService.getCommentByBlogId(blogId));
        return "blog :: commentList";  //局部刷新
    }

    @PostMapping("/comments")   //提交留言
    public String post(Comment comment, HttpSession session){
        Long blogId = comment.getBlog().getId();
        comment.setBlogId(blogId);
        User user = (User) session.getAttribute(Contants.USER_SESSION);
        if (user != null){   //用户为管理员
            comment.setAvatar(user.getAvatar());
            comment.setAdmincomment(true);
        }else {
            Comment comment1 = commentService.getCommentUserByEmailAndNickname(comment.getNickname(),comment.getEmail());
            if(comment1 != null){
                comment.setAvatar(comment1.getAvatar());
            }else{
                comment.setAvatar(RandomAvatarUtils.RandPicture());  //不存在默认随机给张头像
            }
            comment.setAdmincomment(false);
        }
        commentService.saveComment(comment);
        return "redirect:/comments/" + blogId;
    }

    @GetMapping("/message")  //展示留言
    public String comments(Model model){
        model.addAttribute(Contants.BLOG, blogsService.getBlogById(3L));
        model.addAttribute("comments", commentService.getCommentByBlogId(3L));
        return "message-me";
    }
}
