package com.codefans.basicjava.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author: ShengzhiCai
 * @date: 2018-06-21 15:00
 */
public class JdkDateUtils {

    private static final String pattern = "yyyy-MM-dd HH:mm:ss";

    private static ThreadLocal<DateFormat> threadLocal = new ThreadLocal<DateFormat>();

    public static DateFormat getDateFormat() {
        DateFormat df = threadLocal.get();
        if(df == null) {
            //SimpleDateFormat被认为执行慢，且不是线程安全的
            df = new SimpleDateFormat(pattern);
            threadLocal.set(df);
        }
        return df;
    }

    public static String format(Date date) throws ParseException {
        return getDateFormat().format(date);
    }

    public static Date parse(String strDate) throws ParseException {
        return getDateFormat().parse(strDate);
    }





}
