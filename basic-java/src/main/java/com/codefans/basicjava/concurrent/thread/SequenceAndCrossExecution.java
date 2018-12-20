package com.codefans.basicjava.concurrent.thread;

/**
 * @author:
 * @date: 2018-12-17 16:21:41
 * 1，2和5，6两组线程组交替执行，每个线程组内又是顺序执行的，大循环3次
 *
 * 注意:
 *  *    1.notify()方法和wait()方法必须在循环体内调用,否则程序都运行完了再调用,是起不到唤醒和等待的作用的。
 */
public class SequenceAndCrossExecution {

    private Object lock = new Object();
    //1,2,5,6
    private int executeNo = 1;

    private int repeatTimes = 3;

    public static void main(String[] args) {
        SequenceAndCrossExecution sequenceAndCrossExecution = new SequenceAndCrossExecution();
        sequenceAndCrossExecution.startup();
    }

    public void startup() {
        executeInSequence();
    }

    public void executeInSequence() {
        new Thread01().start();
        new Thread02().start();
        new Thread05().start();
        new Thread06().start();
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
                        executeNo = 5;
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

    class Thread05 extends Thread {
        @Override
        public void run() {
            synchronized (lock) {
                for(int i = 0; i < repeatTimes; ) {
                    if(executeNo == 5) {
                        executeNo = 6;
                        //只有线程体有效执行后,下标再增加
                        i ++;
                        System.out.println("Thread05 is running, i=" + i);
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

    class Thread06 extends Thread {
        @Override
        public void run() {
            synchronized (lock) {
                for(int i = 0; i < repeatTimes; ) {
                    if(executeNo == 6) {
                        executeNo = 1;
                        //只有线程体有效执行后,下标再增加
                        i ++;
                        System.out.println("Thread06 is running, i=" + i);
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
