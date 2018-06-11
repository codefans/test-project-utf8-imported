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

    public static byte[] getFileBytes(String filePath) throws IOException {
        File file = new File(filePath);
        if(!file.exists()) {
            throw new FileNotFoundException("[" + filePath + "] file not found.");
        }
        BufferedInputStream bis = null;
        ByteArrayOutputStream baos = null;
        try {
            bis = new BufferedInputStream(new FileInputStream(file));

            byte[] bytes = new byte[1024];
            baos = new ByteArrayOutputStream();
            int n = 0;
            while((n = bis.read(bytes)) != -1) {
                baos.write(bytes, 0, n);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(bis != null) {
                bis.close();
                bis = null;
            }
        }
        return baos.toByteArray();
    }


}
