package com.codefans.basicjava.concurrent.thread;

/**
 * @author:
 * @date: 2018-12-17 16:19:49
 * 线程按顺序执行
 * 1.只顺序执行一遍
 *    按顺序调用start()方法,且每个start方法后面跟一个join()方法,确保前面的线程执行完再启动下一个线程。
 * 2.顺序执行完,再顺序执行一遍,如此循环
 *    (1)用一个整数控制执行哪个线程,如executeNo=1,则第一个线程执行。
 *    (2)executeNo=1时,执行第一个线程,然后设置executeNo=2,这样控制第二个线程执行。
 *    (3)先调用锁对象的notifyAll()方法,再调用wait()方法。
 *    (4)如果规定循环几次,那么循环时,只有线程体有效执行后,下标再增加
 *
 * 注意:
 *    1.notify()方法和wait()方法必须在循环体内调用,否则程序都运行完了再调用,是起不到唤醒和等待的作用的。
 */
public class ExecuteInSequence {

    private Object lock = new Object();
    //1,2,3
    private int executeNo = 1;

    private int repeatTimes = 3;

    public static void main(String[] args) {
        ExecuteInSequence executeInSequence = new ExecuteInSequence();
        executeInSequence.startup();
    }

    public void startup() {
//        executeInSequence01();
        executeInSequence02();
    }

    public void executeInSequence01() {
        try {
            Thread thread01 = new Thread01();
            Thread thread02 = new Thread02();
            Thread thread03 = new Thread03();
            thread01.start();
            //join方法的意思是:等待线程thread01终止
            thread01.join();
            thread02.start();
            thread02.join();
            thread03.start();
            thread03.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void executeInSequence02() {
        new Thread01().start();
        new Thread02().start();
        new Thread03().start();
    }

    class Thread01 extends Thread {
        @Override
        public void run() {
            synchronized (lock) {
                for(int i = 0; i < repeatTimes; ) {
                    if(executeNo == 1) {
                        executeNo = 2;
                        //只有线程体有效执行后,下标再增加
                        i ++;
                        System.out.println("Thread01 is running, i=" + i);
                    }
                    lock.notifyAll();
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    class Thread02 extends Thread {
        @Override
        public void run() {
            synchronized (lock) {
                for(int i = 0; i < repeatTimes; ) {
                    if(executeNo == 2) {
                        executeNo = 3;
                        //只有线程体有效执行后,下标再增加
                        i ++;
                        System.out.println("Thread02 is running, i=" + i);
                    }
                    lock.notifyAll();
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

        }
    }

    class Thread03 extends Thread {
        @Override
        public void run() {
            synchronized (lock) {
                for(int i = 0; i < repeatTimes; ) {
                    if(executeNo == 3) {
                        executeNo = 1;
                        //只有线程体有效执行后,下标再增加
                        i ++;
                        System.out.println("Thread03 is running, i=" + i);
                    }
                    lock.notifyAll();
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

        }
    }

}
