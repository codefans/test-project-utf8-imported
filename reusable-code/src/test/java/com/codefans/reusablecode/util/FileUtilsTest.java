package com.codefans.reusablecode.util;

import org.junit.Test;

/**
 * @author: mpif
 * @date: 2018-06-07 14:10
 */
public class FileUtilsTest {

    @Test
    public void fileToStrTest() {

        String filePath = "/githubForSourcetree/pic_beauty_ranking/src/main/resources/base64Str.txt";
        String fileStr = FileUtils.fileToStr(filePath);
        System.out.println("fileStr:");
        System.out.println(fileStr);

    }



}
