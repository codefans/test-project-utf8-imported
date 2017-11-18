package com.codefans.basicjava.jvm;

/**
 * @author: caishengzhi
 * @date: 2017-11-01 17:41
 **/
public class MinorGCTest {

    private static final int _1MB = 1024 * 1024;

    /**
     *  VM参数：-verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8
     */
    public static void testAllocation() {
        byte[] allocation1, allocation2, allocation3, allocation4;
        allocation1 = new byte[2 * _1MB];
        allocation2 = new byte[2 * _1MB];
        allocation3 = new byte[2 * _1MB];
        allocation4 = new byte[4 * _1MB]; //出现一次Minor GC
    }

    /**
     * VM参数：
     * -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:MaxTenuringThreshold=1
     * -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:MaxTenuringThreshold=15
     * 分别以上面两种VM参数执行下列方法
     *
     * 执行结果：
     * 第一种VM参数：
     * Heap
     PSYoungGen      total 9216K, used 5364K [0x00000000ff600000, 0x0000000100000000, 0x0000000100000000)
     eden space 8192K, 65% used [0x00000000ff600000,0x00000000ffb3d0a0,0x00000000ffe00000)
     from space 1024K, 0% used [0x00000000fff00000,0x00000000fff00000,0x0000000100000000)
     to   space 1024K, 0% used [0x00000000ffe00000,0x00000000ffe00000,0x00000000fff00000)
     PSOldGen        total 10240K, used 8192K [0x00000000fec00000, 0x00000000ff600000, 0x00000000ff600000)
     object space 10240K, 80% used [0x00000000fec00000,0x00000000ff400020,0x00000000ff600000)
     PSPermGen       total 21248K, used 3552K [0x00000000f9a00000, 0x00000000faec0000, 0x00000000fec00000)
     object space 21248K, 16% used [0x00000000f9a00000,0x00000000f9d786c0,0x00000000faec0000)

     第二种VM参数：
     Heap
     PSYoungGen      total 9216K, used 5364K [0x00000000ff600000, 0x0000000100000000, 0x0000000100000000)
     eden space 8192K, 65% used [0x00000000ff600000,0x00000000ffb3d0a0,0x00000000ffe00000)
     from space 1024K, 0% used [0x00000000fff00000,0x00000000fff00000,0x0000000100000000)
     to   space 1024K, 0% used [0x00000000ffe00000,0x00000000ffe00000,0x00000000fff00000)
     PSOldGen        total 10240K, used 8192K [0x00000000fec00000, 0x00000000ff600000, 0x00000000ff600000)
     object space 10240K, 80% used [0x00000000fec00000,0x00000000ff400020,0x00000000ff600000)
     PSPermGen       total 21248K, used 3428K [0x00000000f9a00000, 0x00000000faec0000, 0x00000000fec00000)
     object space 21248K, 16% used [0x00000000f9a00000,0x00000000f9d593d8,0x00000000faec0000)

     */
    public static void testTenuringThreshold() {
        byte[] allocation1, allocation2, allocation3;
        allocation1 = new byte[_1MB / 4];
        allocation2 = new byte[4 * _1MB];
        allocation3 = new byte[4 * _1MB];
        allocation3 = null;
        allocation3 = new byte[4 * _1MB];
    }

    public static void main(String[] args) {
//        testAllocation();
        testTenuringThreshold();
    }

}




























