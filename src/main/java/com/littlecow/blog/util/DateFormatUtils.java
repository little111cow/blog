package com.littlecow.blog.util;


import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatUtils {
    public static String formatDate(Date date){
        SimpleDateFormat formater = new SimpleDateFormat("yyyy-mm--dd");
        String date1 = null;
        try {
           date1  = formater.format(date);
        }catch (Exception e){
            e.printStackTrace();
        }
        return date1;
    }
}
