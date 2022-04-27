package com.littlecow.blog.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
/**
 * 统一异常封装处理
 * */
@ControllerAdvice
public class ControllerExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(Exception.class)
    public ModelAndView handler(HttpServletRequest httpServletRequest, Exception e) throws Exception{
       StringBuffer url =  httpServletRequest.getRequestURL();
       logger.error("Url:",url.toString());
       if(AnnotationUtils.findAnnotation(e.getClass(),ResponseStatus.class) !=null){
            throw e;
       }
       ModelAndView modelAndView = new ModelAndView();
       modelAndView.addObject("Request Url:",url);
       modelAndView.addObject("Exception",e);
       modelAndView.setViewName("error/error");
       return modelAndView;
    }
}
