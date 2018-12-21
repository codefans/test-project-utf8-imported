package com.codefans.basicjava.concurrent.thread;

import java.util.Date;

/**
 * @author: codefans
 * @date: 2018-12-21 10:24:13
 */
public class RunnableAndThread {

    public static void main(String[] args) {
        RunnableAndThread runnableAndThread = new RunnableAndThread();
        runnableAndThread.startup();
    }

    public void startup() {

        Runnable r = new Runnable() {
            @Override
            public void run() {
                System.out.println("name:" + Thread.currentThread().getName() + ", time:" + new Date());
            }
        };

        new Thread(r, "Thread01").start();
        new Thread(r, "Thread02").start();
        new Thread(r, "Thread03").start();
        new Thread(r, "Thread04").start();
        new Thread(r, "Thread05").start();

    }


}
