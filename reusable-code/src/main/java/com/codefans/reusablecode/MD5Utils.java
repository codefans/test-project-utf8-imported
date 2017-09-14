package com.codefans.reusablecode;

import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;

/**
 * @author: caishengzhi
 * @date: 2017-09-14 10:33
 * MD5工具类
 **/
public class MD5Utils {

    public static String getMD5Str(InputStream in) {
        try {
            byte[] strTemp = getBytes(in);
            return getMD5Str(strTemp);
        } catch (Exception e) {
            return null;
        }
    }

    public static String getMD5Str(String source) {
        try {
            byte[] strTemp = source.getBytes();
            return getMD5Str(strTemp);
        } catch (Exception e) {
            return null;
        }
    }

    public static String getMD5Str(byte[] sBytes) {
        char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
        try {

            int j = sBytes.length;

            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = sBytes[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);

        } catch (Exception e) {
            return null;
        }
    }

    /**
     *
     * @param inputStream
     * @return
     */
    public static byte[] getBytes(InputStream inputStream) {
        byte[] bytes = new byte[8192];
        int read = 0;
        byte[] md5Bytes = null;
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            int totalBytes = 0;
            while ((read = inputStream.read(bytes, 0, 8192)) > 0) {
                totalBytes = totalBytes + read;
                md5.update(bytes, 0, read);
            }
            System.out.println("totalBytes:[" + totalBytes + "]");
            md5Bytes = md5.digest();
        } catch (IOException ioex) {
            ioex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return md5Bytes;
    }

}
