package com.codefans.basicjava.util;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Date;

/**
 * @author: ShengzhiCai
 * @date: 2018-06-21 15:41
 */
public class JodaDateUtils {

    private static final String pattern = "yyyy-MM-dd HH:mm:ss";

    public static String format(Date date) {
        DateTime dateTime = new DateTime(date);
        return dateTime.toString(pattern);
    }

    public static Date parse(String dateStr) {
        DateTimeFormatter dtf = DateTimeFormat.forPattern(pattern);
        DateTime dateTime = dtf.parseDateTime(dateStr);
        return dateTime.toDate();
    }

}
