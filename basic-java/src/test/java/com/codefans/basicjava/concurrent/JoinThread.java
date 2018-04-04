package com.codefans.basicjava.concurrent;

import java.util.Date;

/**
 * Created by codefans on 2018/3/30.
 */
public class JoinThread extends Thread {

    private String threadName;
    public JoinThread(String threadName) {
        this.threadName = threadName;
    }

    public void run() {

        try {

            Thread.sleep(3 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("threadName=" + threadName + ", time:" + new Date());
    }

}
