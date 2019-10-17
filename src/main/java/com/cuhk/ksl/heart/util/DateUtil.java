package com.cuhk.ksl.heart.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    public static String generalSimpleDateFormat(){

        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }
}
