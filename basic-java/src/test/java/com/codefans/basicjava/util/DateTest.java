package com.codefans.basicjava.util;

import org.junit.Test;

import java.text.ParseException;
import java.util.Date;

/**
 * @author: ShengzhiCai
 * @date: 2018-06-21 15:34
 */
public class DateTest {

    @Test
    public void commonsDateUtilsTest() {

        try {

            System.out.println("commons-lang日期处理:");

            String dateStr = CommonsDateUtils.format(new Date());
            System.out.println("dateStr:" + dateStr);

            Date date = CommonsDateUtils.parse(dateStr);
            System.out.println("date:" + date);

        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void jdkDateUtilsTest() {

        try {

            System.out.println("jdk日期处理:");

            String dateStr = JdkDateUtils.format(new Date());
            System.out.println("dateStr:" + dateStr);

            Date date = JdkDateUtils.parse(dateStr);
            System.out.println("date:" + date);

        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void jodaDateUtilsTest() {


        System.out.println("joda日期处理:");

        String dateStr = JodaDateUtils.format(new Date());
        System.out.println("dateStr:" + dateStr);

        Date date = JodaDateUtils.parse(dateStr);
        System.out.println("date:" + date);


    }

}
