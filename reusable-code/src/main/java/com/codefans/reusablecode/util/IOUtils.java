package com.codefans.reusablecode.util;

import java.io.*;

/**
 * @Author: ShengzhiCai
 * @Date: 2017-09-26 23:35
 */

public class IOUtils {

    String path;

    FileWriter fw = null;

    public IOUtils(String path) throws IOException {
        fw = new FileWriter(new File(path));
    }

    public void append(String path) throws IOException {
        fw.append(path).append("/r/n");
    }

    public void close() throws IOException {
        if(fw != null) {
            fw.flush();
            fw.close();
            fw = null;
        }
    }

    public static String getStr(InputStream is) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] bytes = new byte[1024];
        int n = 0;
        while((n = is.read(bytes)) != -1) {
            baos.write(bytes, 0, n);
        }
        baos.flush();
        return baos.toString("UTF-8");
    }


}
