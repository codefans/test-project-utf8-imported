package com.codefans.basicjava.byteopt;

/**
 * @author: codefans
 * @date: 2019-01-23 18:28:13
 */
public class ByteMain {

    public static void main(String[] args) {
        ByteMain bitMain = new ByteMain();
        bitMain.test();
    }

    /**
     * HashMap中定位槽的方法:
     * static int indexFor(int h, int length) {
     *      return h & (length-1);
     * }
     *
     * 其中只有length为2的幂时,h&(length-1)才会等于h%length
     */
    public void test() {

        System.out.println("5%2=" + 5%2);
        System.out.println("(5 & (2 - 1))=" + (5 & (2 - 1)));

        System.out.println("7%4=" + 7%4);
        System.out.println("(7 & (4 - 1))=" + (7 & (4 - 1)));

        System.out.println("13%8=" + 13%8);
        System.out.println("(13 & (8 - 1))=" + (13 & (8 - 1)));

        System.out.println("23%16=" + 23%16);
        System.out.println("(23 & (16 - 1))=" + (23 & (16 - 1)));


    }


}
