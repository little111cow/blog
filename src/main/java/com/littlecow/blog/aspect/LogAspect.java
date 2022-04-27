package com.littlecow.blog.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Arrays;

/**
 * Aop实现日志记录
 * */
@Aspect
@Component
public class LogAspect {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /*封装日志对象*/
    private class RequestLog{
        private String ip;  //请求的主机ip
        private String url; //请求的url
        private String classMethod; //请求对应的类和方法
        private Object[] args;  //请求的参数

        public RequestLog(String ip, String url, String classMethod, Object[] args) {
            this.ip = ip;
            this.url = url;
            this.classMethod = classMethod;
            this.args = args;
        }

        public RequestLog() {

        }

        @Override
        public String toString() {
            return "RequestLog{" +
                    "ip='" + ip + '\'' +
                    ", url='" + url + '\'' +
                    ", ClassMethod='" + classMethod + '\'' +
                    ", args=" + Arrays.toString(args) +
                    '}';
        }
    }
    //切入点定义
    @Pointcut("execution(* com.littlecow.blog.controller.*.*(..))")
    public void log(){}

    //JoinPoint可以用来获取一些运行时的必要信息
    @Before("log()")
    public void beforeLog(JoinPoint joinPoint){
        logger.info("--------------------------doBefore-----------------------------");
        ServletRequestAttributes attributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        String ip = attributes.getRequest().getRemoteAddr();
        String url = attributes.getRequest().getRequestURL().toString();
        String classMethod = joinPoint.getSignature().getDeclaringTypeName()+"."+joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        RequestLog requestLog = new RequestLog(ip,url,classMethod,args);
        logger.info("Request details:{}",requestLog);
    }

    @After("log()")
    public void afterLog(){
//        logger.info("---------------------------doAfter-----------------------------");
    }

    @AfterReturning(pointcut = "log()",returning = "result" )
    public void afterReturnLog(Object result){
        logger.info("------------------------doAfterReturn----------------------------");
        logger.info("Result:{}",result);
    }
}
