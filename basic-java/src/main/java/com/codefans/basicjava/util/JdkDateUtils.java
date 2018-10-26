package com.codefans.basicjava.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: ShengzhiCai
 * @date: 2018-06-21 15:00
 *
 * SimpleDateFormat线程不安全及解决办法
 * https://blog.csdn.net/csdn_ds/article/details/72984646
 *
 */
public class JdkDateUtils {

    /**
     * 锁对象
     */
    private static final Object lockObj = new Object();

    private static final String pattern = "yyyy-MM-dd HH:mm:ss";

    private static ThreadLocal<DateFormat> threadLocal = new ThreadLocal<DateFormat>();

    private static Map<String, ThreadLocal<SimpleDateFormat>> sdfMap = new ConcurrentHashMap<String, ThreadLocal<SimpleDateFormat>>();

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



    private static SimpleDateFormat getSdf(final String pattern) {

        ThreadLocal<SimpleDateFormat> tl = sdfMap.get(pattern);

        // 此处的双重判断和同步是为了防止sdfMap这个单例被多次put重复的sdf
        if (tl == null) {
            synchronized (lockObj) {
                tl = sdfMap.get(pattern);
                if (tl == null) {
                    // 只有Map中还没有这个pattern的sdf才会生成新的sdf并放入map
                    System.out.println("put new sdf of pattern " + pattern + " to map");

                    // 这里是关键,使用ThreadLocal<SimpleDateFormat>替代原来直接new SimpleDateFormat
                    tl = new ThreadLocal<SimpleDateFormat>() {

                        @Override
                        protected SimpleDateFormat initialValue() {
                            System.out.println("thread: " + Thread.currentThread() + " init pattern: " + pattern);
                            return new SimpleDateFormat(pattern);
                        }
                    };
                    sdfMap.put(pattern, tl);
                }
            }
        }

        return tl.get();
    }

    /**
     * 使用ThreadLocal<SimpleDateFormat>来获取SimpleDateFormat,这样每个线程只会有一个SimpleDateFormat
     * 如果新的线程中没有SimpleDateFormat，才会new一个
     * @param date
     * @param pattern
     * @return
     */
    public static String format(Date date, String pattern) {
        return getSdf(pattern).format(date);
    }

    public static Date parse(String dateStr, String pattern) throws ParseException {
        return getSdf(pattern).parse(dateStr);
    }




}
