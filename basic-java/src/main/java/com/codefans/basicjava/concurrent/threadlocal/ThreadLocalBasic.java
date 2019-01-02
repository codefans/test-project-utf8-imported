package com.codefans.basicjava.concurrent.threadlocal;

/**
 * @author:
 * @date: 2018-12-17 16:18:57
 * ThreadLocal特性测试
 *
 *
 *
 *
 */
public class ThreadLocalBasic {

    private ThreadLocal<Integer> dataThreadLocal = new ThreadLocal<Integer>();

    public static void main(String[] args) {
        ThreadLocalBasic threadLocalBasic = new ThreadLocalBasic();
        threadLocalBasic.startup();
    }

    public void startup() {
        new Thread01().start();
        new Thread02().start();
    }

    class Thread01 extends Thread {
        @Override
        public void run() {
            dataThreadLocal.set(1);

            System.out.println("Thread01 running...");

            System.out.println("data in Thread01 is:" + dataThreadLocal.get());

        }
    }

    class Thread02 extends Thread {
        @Override
        public void run() {
            dataThreadLocal.set(2);

            System.out.println("Thread01 running...");

            System.out.println("data in Thread01 is:" + dataThreadLocal.get());
        }
    }



}
