package com.codefans.reusablecode.util;

import org.junit.Test;

/**
 * @author: caishengzhi
 * @date: 2017-09-14 10:40
 * Md5Utils类单元测试
 **/
public class Md5UtilsTest {

    @Test
    public void getMd5StrTest() {
        String sourceStr = "hello";
        String md5Str = Md5Utils.getMd5Str(sourceStr);
        System.out.println("source:" + sourceStr);
        System.out.println("md5Str:" + md5Str);
        System.out.println("sourceStr.length:" + sourceStr.length());
        System.out.println("md5Str.length:" + md5Str.length());
        System.out.println("sourceStr.length==md5Str.length:" + (sourceStr.length()==md5Str.length()));
    }

}
