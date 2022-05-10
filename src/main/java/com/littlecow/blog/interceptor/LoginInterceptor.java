package com.littlecow.blog.interceptor;

import com.littlecow.blog.Contants;
import com.littlecow.blog.entity.User;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * create 2022.4.18 by liujian
 * 登录拦截器
 * */
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        User user =(User) request.getSession().getAttribute(Contants.USER_SESSION);
        if(user == null){
            response.sendRedirect("/admin");
            return false;
        }
        return true;
    }
}
