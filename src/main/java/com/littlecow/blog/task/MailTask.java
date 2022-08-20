package com.littlecow.blog.task;

import com.littlecow.blog.entity.User;
import com.littlecow.blog.service.UserLoginService;
import com.littlecow.blog.util.MailUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 定时发送邮件任务
 * */
@Component
@EnableScheduling
@EnableAsync
public class MailTask {
    @Resource
    private UserLoginService userLoginService;

    @Async
    @Scheduled(cron = "0 0 18 * * ?") // 每天18点发送一次
    public void webLiveCheck(){
        String content = "网站正常运行中，服务正常!";
        User  user = userLoginService.getUserById(1L);
        String mail = user.getEmail();
        MailUtils.sendMail(mail, "博客系统运行状态", content);
    }
}
