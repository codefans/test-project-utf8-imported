package com.codefans.reusablecode;

import com.codefans.reusablecode.util.MD5Utils;
import org.junit.Test;

/**
 * @author: caishengzhi
 * @date: 2017-09-14 10:40
 * MD5Utils类单元测试
 **/
public class MD5UtilsTest {

    @Test
    public void getMD5StrTest() {
        String sourceStr = "hello";
        String md5Str = MD5Utils.getMD5Str(sourceStr);
        System.out.println("source:" + sourceStr);
        System.out.println("md5Str:" + md5Str);
        System.out.println("sourceStr.length:" + sourceStr.length());
        System.out.println("md5Str.length:" + md5Str.length());
        System.out.println("sourceStr.length==md5Str.length:" + (sourceStr.length()==md5Str.length()));
    }

}
