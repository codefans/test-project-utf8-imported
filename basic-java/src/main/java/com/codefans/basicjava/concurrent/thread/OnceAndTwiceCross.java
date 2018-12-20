package com.codefans.basicjava.concurrent.thread;

/**
 * @author: codefans
 * @date: 2018-12-20 15:57:05
 * 一个线程执行1次, 一个线程执行2次, 如此循环3次
 *
 * 注意:
 *  *    1.notify()方法和wait()方法必须在循环体内调用,否则程序都运行完了再调用,是起不到唤醒和等待的作用的。
 */
public class OnceAndTwiceCross {

    private Object lock = new Object();

    private int repeatTimes = 3;

    //1,2,3
    private int executeNo = 1;

    public static void main(String[] args) {
        OnceAndTwiceCross onceAndTwiceCross = new OnceAndTwiceCross();
        onceAndTwiceCross.startup();
    }

    public void startup() {
        new Thread01().start();
        new Thread02().start();
    }

    class Thread01 extends Thread {
        @Override
        public void run() {
            synchronized(lock) {
                for(int i = 0; i < repeatTimes; ) {

                    if(executeNo == 1 || executeNo == 2) {
                        if(executeNo == 1) {
                            executeNo = 2;
                            majorBusinessLogic();
                        }
                        if(executeNo == 2) {
                            executeNo = 3;
                            i ++;
                            majorBusinessLogic();
                        }
                    }

                    lock.notify();
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        }
        public void majorBusinessLogic() {
            System.out.println("Thread01 is running...");
        }
    }

    class Thread02 extends Thread {
        @Override
        public void run() {
            synchronized(lock) {
                for(int i = 0; i < repeatTimes; ) {

                    if(executeNo == 3) {
                        executeNo = 1;
                        i ++;
                        majorBusinessLogic();
                    }

                    lock.notify();
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        }
        public void majorBusinessLogic() {
            System.out.println("Thread02 is running...");
        }
    }

}
