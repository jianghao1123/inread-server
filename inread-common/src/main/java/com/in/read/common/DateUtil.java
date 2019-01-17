package com.in.read.common;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by luyun on 2019/1/16.
 */
public class DateUtil {

    public static final String MD_FORMAT = "MM-dd";

    public static String date2Str(Date date, String pattern) {
        if (date == null || date.equals("")){
            return null;
        }
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        return formatter.format(date);
    }
}
