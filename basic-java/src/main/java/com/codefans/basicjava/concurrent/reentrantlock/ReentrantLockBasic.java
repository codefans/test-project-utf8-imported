package com.codefans.basicjava.concurrent.reentrantlock;

import java.util.Date;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author:
 * @date: 2018-12-17 09:55:00
 */
public class ReentrantLockBasic {

    public static void main(String[] args) {
        ReentrantLockBasic reentrantLockBasic = new ReentrantLockBasic();
        reentrantLockBasic.runCode();

    }

    public void runCode() {
        final ReentrantLock reentrantLock = new ReentrantLock(true);

        int threadNum = 5;

        for(int i = 0; i < threadNum; i ++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    reentrantLock.tryLock();

                    System.out.println("name:" + Thread.currentThread().getName() + ", date:" + new Date());

                    reentrantLock.unlock();


                }
            }, "thread_no." + (i+1)).start();
        }


    }
}
