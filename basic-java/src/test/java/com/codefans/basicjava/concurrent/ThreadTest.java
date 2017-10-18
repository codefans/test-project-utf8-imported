package com.codefans.basicjava.concurrent;

import org.junit.Test;

import java.util.Date;

/**
 * @Author: ShengzhiCai
 * @Date: 2017-09-29 18:05
 */

public class ThreadTest {

    @Test
    public void startRunCompareTest() {

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("threadName:" + Thread.currentThread().getName() + ", time:" + new Date());

                try {
                    Thread.sleep(2 * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("thread end, time:" + new Date());

            }
        });

//        t.run();
        t.start();

        System.out.println("---->time:" + new Date());

        try {
            Thread.sleep(5 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }



    }




}
