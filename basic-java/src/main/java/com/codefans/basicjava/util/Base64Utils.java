package com.codefans.basicjava.util;

/**
 * @author: mpif
 * @date: 2018-06-08 17:28
 * Base64编码说明
　　Base64编码要求把3个8位字节（3*8=24）转化为4个6位的字节（4*6=24），之后在6位的前面补两个0，形成8位一个字节的形式。
   如果剩下的字符不足3个字节，则用0填充，输出字符使用'='，因此编码后输出的文本末尾可能会出现1或2个'='。

　　为了保证所输出的编码位可读字符，Base64制定了一个编码表，以便进行统一转换。编码表的大小为2^6=64，这也是Base64名称的由来。

   The Base64 index table
   https://en.wikipedia.org/wiki/Base64#RFC_3548

 */
public class Base64Utils {

    private static final char[] encodedChars = new char[] {
        'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
        'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T',
        'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd',
        'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n',
        'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x',
        'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7',
        '8', '9', '+', '/'
    };

    public static String encode(byte[] bytes) {

        StringBuilder sb = new StringBuilder();

        Byte.parseByte("", 2);
        Integer.parseInt("", 2);

        for(int i = 0; i < bytes.length; i ++) {

        }

        return "";
    }

    public static byte[] decode(String encodeStr) {

        return new byte[0];
    }

}
