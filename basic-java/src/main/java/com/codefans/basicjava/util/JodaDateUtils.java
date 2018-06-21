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

    public static final String yyyyMMddHHmmss = "yyyy-MM-dd HH:mm:ss";
    public static final String yyyyMMddHHmmssSSS = "yyyy-MM-dd HH:mm:ss SSS";

    public static String format(Date date) {
        DateTime dateTime = new DateTime(date);
        return dateTime.toString(yyyyMMddHHmmss);
    }

    public static String format(Date date, String pattern) {
        DateTime dateTime = new DateTime(date);
        return dateTime.toString(pattern);
    }

    public static Date parse(String dateStr) {
        DateTimeFormatter dtf = DateTimeFormat.forPattern(yyyyMMddHHmmss);
        DateTime dateTime = dtf.parseDateTime(dateStr);
        return dateTime.toDate();
    }

    public static Date parse(String dateStr, String pattern) {
        DateTimeFormatter dtf = DateTimeFormat.forPattern(pattern);
        DateTime dateTime = dtf.parseDateTime(dateStr);
        return dateTime.toDate();
    }

    public static long parseMillis(String dateStr) {
        DateTimeFormatter dtf = DateTimeFormat.forPattern(yyyyMMddHHmmssSSS);
        DateTime dateTime = dtf.parseDateTime(dateStr);
        return dateTime.getMillis();
    }

    public static String formatMillis(long dateMillis) {
        DateTime dateTime = new DateTime(dateMillis);
        return dateTime.toString(yyyyMMddHHmmss);
    }

    public static int parseSecondOfMinute(String dateStr) {
        DateTimeFormatter dtf = DateTimeFormat.forPattern(yyyyMMddHHmmssSSS);
        DateTime dateTime = dtf.parseDateTime(dateStr);
        return dateTime.getSecondOfMinute();
    }

    public static String formatSeconds(long dateMillis) {
        DateTime dateTime = new DateTime(dateMillis);
        return dateTime.toString(yyyyMMddHHmmss);
    }

}
