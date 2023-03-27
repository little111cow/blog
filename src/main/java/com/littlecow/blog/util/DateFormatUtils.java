package com.littlecow.blog.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatUtils {
    public static String dateFormat(Date date) {
        SimpleDateFormat formater = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        return formater.format(date);
    }
}
