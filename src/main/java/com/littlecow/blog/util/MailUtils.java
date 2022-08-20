package com.littlecow.blog.util;

import com.sun.mail.util.MailSSLSocketFactory;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * 邮箱发送工具类
 */
public class MailUtils {
    /**
     * 发送验证码
     *
     * @param receiveMail 接收邮件的邮件地址
     */
    public static void sendMail(String receiveMail, String title, String content) {
        Properties prop = new Properties();
        // 开启debug调试，以便在控制台查看
        prop.setProperty("mail.debug", "true");
        // 设置邮件服务器主机名
        prop.setProperty("mail.host", "smtp.163.com");
        // 发送服务器需要身份验证
        prop.setProperty("mail.smtp.auth", "true");
        // 发送邮件协议名称
        prop.setProperty("mail.transport.protocol", "smtp");
        // 开启SSL加密，否则会失败
        try {
            MailSSLSocketFactory sf = new MailSSLSocketFactory();
            sf.setTrustAllHosts(true);
            prop.put("mail.smtp.ssl.enable", "true");
            prop.put("mail.smtp.ssl.socketFactory", sf);
            // 创建session
            Session session = Session.getInstance(prop);
            // 通过session得到transport对象
            Transport ts = session.getTransport();
            // 连接邮件服务器：邮箱类型，帐号，POP3/SMTP协议授权码 163使用：smtp.163.com，qq使用：smtp.qq.com
            ts.connect("smtp.163.com", "blog_system_server@163.com", "KBANGKROPISGLOTY");
            // 创建邮件
            Message message = createSimpleMail(session, receiveMail, title, content);
            // 发送邮件
            ts.sendMessage(message, message.getAllRecipients());
            ts.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * @Method: createSimpleMail 邮件内容创建
     * @Description: 创建一封只包含文本的邮件
     */
    private static MimeMessage createSimpleMail(Session session, String receiveMail, String title, String content)
            throws Exception {
        // 创建邮件对象
        MimeMessage message = new MimeMessage(session);
        // 指明邮件的发件人
        message.setFrom(new InternetAddress("blog_system_server@163.com"));
        // 指明邮件的收件人，现在发件人和收件人是一样的，那就是自己给自己发
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(receiveMail));
        // 邮件的标题
        message.setSubject(title);
        // 邮件的文本内容
        message.setContent(content, "text/html;charset=UTF-8");

        // 返回创建好的邮件对象
        return message;
    }
}
