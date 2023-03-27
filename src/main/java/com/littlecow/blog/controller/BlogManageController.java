package com.littlecow.blog.controller;

import com.littlecow.blog.entity.User;
import com.littlecow.blog.service.UserLoginService;
import com.littlecow.blog.util.MailUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
@RequestMapping("/admin")
public class BlogManageController {
    @Resource
    private UserLoginService userLoginService;

    @RequestMapping("/checkBlog")
    public String checkBlog() {
        String title = "博客服务端运行状态";
        String content = "博客服务端正常运行，如不能正常访问，请检查服务器公网状态和服务器防火墙。";
        User user = userLoginService.getUserById(1L);
        String mail = user.getEmail();
        MailUtils.sendMail(mail, title, content);
        return "admin/index";
    }
}
