package com.codefans.basicjava.java6.lang;

import org.junit.Test;

import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author: caishengzhi
 * @date: 2017-10-23 11:23
 **/
public class ThreadTest {

    @Test
    public void threadTest() {
        this.threadMaxNumTest();
    }

    public static void main(String[] args) {
        ThreadTest tt = new ThreadTest();
        tt.threadMaxNumTest();
    }

    public void threadMaxNumTest() {
        int threadMaxNum = 1000;
        int threadIndex = 0;
        final AtomicLong num = new AtomicLong();

        while(threadMaxNum > 0) {
//            System.out.print("第[" + (++threadIndex) + "]个线程, ");
            new Thread(new Runnable() {
                @Override
                public void run() {
//                    try {
//                        Thread.sleep(1 * 1000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
                    int loopNum = 1000000000;
                    while(loopNum > 0) {
                        new String();

                        loopNum--;
                    }



                    System.out.println("第[" + (num.incrementAndGet()) + "]个线程, time:" + new Date());
                }
            }).start();
            threadMaxNum--;
        }
        System.out.println("创建线程结束。。。");

    }

}
