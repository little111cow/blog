package com.littlecow.blog.util;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class LoginCodeUtils {
    public static String getRandomCode(int length){
        //获得四位随机验证码串恒为四位
        String codeBase = "0123456789ABCDEFGHJKLMNPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";//随机字符集
        Random random = new Random();
        StringBuffer sb= new StringBuffer();
        for(int i=0;i<length;i++){
            sb.append(codeBase.charAt(random.nextInt(codeBase.length())));
        }
        return sb.toString();
    }

    public static BufferedImage getLoginImage(String code){
        //用BufferImage实现验证码
        BufferedImage image = new BufferedImage(80,20,BufferedImage.TYPE_INT_RGB);
        //向空图写内容的对象
        Graphics2D graphic = (Graphics2D)image.getGraphics();
        //设置背景颜色
        graphic.setBackground(Color.white);
        //设置背景
        graphic.setColor(Color.lightGray);
        //填充一个矩形框
        graphic.fillRect(0,0,80,20);
        //写字体颜色
        graphic.setColor(Color.black);
        //字体属性
        graphic.setFont(new Font(null,Font.BOLD,20));
        //写入字符串
        graphic.drawString(code,20,20);
        return image;
    }
}
