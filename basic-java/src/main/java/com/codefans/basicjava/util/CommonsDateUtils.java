package com.codefans.basicjava.util;

import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.lang.time.FastDateFormat;

import java.text.ParseException;
import java.util.Date;

/**
 * @author: ShengzhiCai
 * @date: 2018-06-21 15:00
 */
public class CommonsDateUtils {

    private static final String pattern = "yyyy-MM-dd HH:mm:ss";

    public static FastDateFormat fdf = FastDateFormat.getInstance(pattern);

    public static String format(Date date) {
        return fdf.format(date);
    }

    public static Date parse(String dateStr) throws ParseException {
        return DateUtils.parseDate(dateStr, new String[]{pattern});
    }

}
