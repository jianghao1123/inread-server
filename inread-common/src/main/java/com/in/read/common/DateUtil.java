package com.in.read.common;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by luyun on 2019/1/16.
 */
public class DateUtil {

    public static final String MD_FORMAT = "MM-dd";

    private static final String[] WEEK_DAYS = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};


    public static String date2Str(Date date, String pattern) {
        if (date == null || date.equals("")) {
            return null;
        }
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        return formatter.format(date);
    }

    /**
     * 获取当前日期是星期几<br>
     *
     * @param date
     * @return 当前日期是星期几
     */
    public static final String getWeekOfDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return WEEK_DAYS[w];
    }
}
