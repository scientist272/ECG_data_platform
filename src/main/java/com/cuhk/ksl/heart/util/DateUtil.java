package com.cuhk.ksl.heart.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
    public static String generalSimpleDateFormat(){

        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }

    public  static String getDaysBefore(int day){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE,-day);
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(calendar.getTime());
    }
}
