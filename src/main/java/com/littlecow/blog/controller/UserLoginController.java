package com.littlecow.blog.controller;

import com.littlecow.blog.Contants;
import com.littlecow.blog.entity.User;
import com.littlecow.blog.service.UserLoginService;
import com.littlecow.blog.util.LoginCodeUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * create by liujiann @2022.4.27
 * 经验：将对象传给前端时最好先做非空判断，避免服务器错误
 * */

@Controller
@RequestMapping("/admin")
public class UserLoginController {
    @Resource
    private UserLoginService userLoginService;

    @RequestMapping("/freshCode")
    @ResponseBody
    public void vcode(HttpServletResponse resp){
        //设置浏览器5秒自动更新
        resp.setHeader("refresh","5");
        //获得验证码字符串
        String code = LoginCodeUtils.getRandomCode(4);
        userLoginService.updateVcode(code);  //保存验证码到数据库便于登录时验证
        //用BufferImage实现验证码并写入code
        BufferedImage image = LoginCodeUtils.getLoginImage(code);
        //http响应内容类型
        resp.setContentType("image/png");
        //浏览器缓存策略
        resp.setDateHeader("expires",-1);
        resp.setHeader("Cache-Control","no-cache");
        resp.setHeader("Pragma","no-cache");

        //用ImageIO写入浏览器
        try {
            ImageIO.write(image,"png",resp.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //登录页
    @RequestMapping
    public String loginPage(){
        return "admin/login";
    }

    @RequestMapping("/email")
    public String loginByEmail(){
        return "admin/login-email";
    }

    //登录用户校验
    @RequestMapping("/login")
    public String login(@RequestParam String vcode,@RequestParam String username, @RequestParam String password,
                        HttpSession session, RedirectAttributes attributes){
        User user = userLoginService.checkUserByUsernameAndPassword(username,password);
        if(user != null&&StringUtils.pathEquals(username,user.getUsername())
                &&StringUtils.pathEquals(password,user.getPassword())){
            if(!StringUtils.pathEquals(vcode.toLowerCase(),userLoginService.getVcode().toLowerCase())){  //忽略大小写区别
                attributes.addFlashAttribute(Contants.MESSAGE,"验证码错误!");
                return "redirect:/admin";
            }
            session.setAttribute(Contants.USER_SESSION,user);  //保存登录状态
            return "admin/index";
        }else{
            attributes.addFlashAttribute(Contants.MESSAGE,"用户名字或者密码错误");
            return "redirect:/admin";
        }
    }

    //登录用户校验
    @RequestMapping("/loginByEmail")
    public String loginByEmail(@RequestParam String email, @RequestParam String mailcode,
                        HttpSession session, RedirectAttributes attributes){
        return "admin/login-email";
    }

    @RequestMapping("/freshEmailCode")
    public void freshEmailCode(@RequestParam String email){

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

    @GetMapping("/index")
    public String adminIndex(){
        return "admin/index";
    }
}
