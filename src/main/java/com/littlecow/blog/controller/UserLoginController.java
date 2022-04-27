package com.littlecow.blog.controller;

import com.littlecow.blog.Contants;
import com.littlecow.blog.entity.User;
import com.littlecow.blog.service.UserLoginService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
/**
 * create by liujiann @2022.4.27
 * 经验：将对象传给前端时最好先做非空判断，避免服务器错误
 * */

@Controller
@RequestMapping("/admin")
public class UserLoginController {
    @Resource
    private UserLoginService userLoginService;

    //登录页
    @RequestMapping
    public String loginPage(){
        return "admin/login";
    }

    //登录用户校验
    @RequestMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, HttpSession session, RedirectAttributes attributes){
        User user = userLoginService.checkUserByUsernameAndPassword(username,password);
        if(user != null&&StringUtils.pathEquals(username,user.getUsername())&&StringUtils.pathEquals(password,user.getPassword())){
            session.setAttribute(Contants.USER_SESSION,user);  //保存登录状态
            return "admin/index";
        }else{
            attributes.addFlashAttribute(Contants.MESSAGE,"用户名字或者密码错误");
            return "redirect:/admin";
        }
    }

    //注销登录
    @RequestMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute(Contants.USER_SESSION);
        return "redirect:/admin";
    }

    @RequestMapping("/editUser")
    public String toEditUser(Model model, HttpSession session,RedirectAttributes attributes){
       User user = (User)session.getAttribute(Contants.USER_SESSION);
       if( user == null) {
           attributes.addFlashAttribute(Contants.MESSAGE,"还没登陆，请登录！");
           return "redirect:/admin";
       }
        model.addAttribute("user", user);
        return "admin/user-edit";
    }

    @PostMapping("/editUser")
    public String EditUser(User user, RedirectAttributes attributes,HttpSession session){
        User user1 = (User)session.getAttribute(Contants.USER_SESSION);
        user.setId(user1.getId());
        boolean isOk = userLoginService.updateUser(user);
        if(isOk && (!StringUtils.pathEquals(user.getPassword(),user1.getPassword())|| !StringUtils.pathEquals(user.getUsername(),user1.getUsername()))){
            attributes.addFlashAttribute(Contants.MESSAGE,"用户名或者密码已经修改，重新登录");
            session.removeAttribute(Contants.USER_SESSION);
            return "redirect:/admin";
        }
        if(isOk){
            session.setAttribute(Contants.USER_SESSION,user);
            attributes.addFlashAttribute(Contants.MESSAGE,"信息修改成功！");
        }else{
            attributes.addFlashAttribute(Contants.MESSAGE,"信息修改失败！");
        }
        return "redirect:/admin/editUser";
    }
}
