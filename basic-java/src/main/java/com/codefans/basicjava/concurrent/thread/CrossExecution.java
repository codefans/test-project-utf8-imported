package com.codefans.basicjava.concurrent.thread;

import java.util.Date;

/**
 * @author:
 * @date: 2018-12-17 16:20:01
 * 两个线程交叉执行,一个打印奇数,一个打印偶数
 * 程序流程:
 *    1.两个线程共用一把锁
 *    2.先调用锁的notify()方法,再调用锁的wait()方法
 *    3.notify()方法和wait()方法必须在循环体内调用,否则程序都运行完了再调用,是起不到唤醒和等待的作用的。
 * 注意:
 *    如果有要求先打奇数,再打偶数,则先启动奇数线程,且循环体下标从1开始
 *
 * 参考资料：
 * 两个线程严格交替执行java实现
 * https://blog.csdn.net/springlustre/article/details/79450754
 */
public class CrossExecution {

    private static Object lock = new Object();

    public static void main(String[] args) {
        CrossExecution crossExecution = new CrossExecution();
        crossExecution.runCode();
    }

    public void runCode() {

        new Thread01().start();
        new Thread02().start();


    }

    class Thread01 extends Thread {

        @Override
        public void run() {

            synchronized(lock) {
                for (int i = 1; i < 100; i++) {
                    //打印奇数
                    if (i % 2 != 0) {
                        System.out.println("打印奇数:" + i);
                    }// end if
                    lock.notify();
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }//end for
            }


        }

    }

    class Thread02 extends Thread {
        @Override
        public void run() {

            synchronized(lock) {
                for (int i = 0; i < 100; i++) {
                    //打印偶数
                    if (i % 2 == 0) {
                        System.out.println("打印偶数:" + i);
                    }
                    lock.notify();
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }//end for
            }
        }

    }

}
