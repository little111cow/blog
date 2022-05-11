package com.littlecow.blog.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * web配置
 * */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    //拦截器拦截的请求和筛除的请求
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/admin/**")
                .excludePathPatterns("/admin","/admin/login","/admin/freshCode");
    }
}
