package com.codefans.basicjava.concurrent.countdownlatch;

import java.util.concurrent.CountDownLatch;

/**
 * @author: ShengzhiCai
 * @date: 2018-10-10 09:41
 * CountDownLatch一般用于某个线程A等待若干个其他线程执行完任务之后，它才执行
 */
public class CountDownLatchBasic {

    public static void main(String[] args) {
        CountDownLatchBasic countDownLatchBasic = new CountDownLatchBasic();
        countDownLatchBasic.basicFunc();
    }

    public void basicFunc() {

        int threadNum = 5;
        final CountDownLatch latch = new CountDownLatch(threadNum);

        for(int i = 0; i < threadNum; i ++) {
            new Thread() {
                public void run() {
                    try {

                        System.out.println("子线程" + Thread.currentThread().getName() + "正在执行");
                        Thread.sleep(3000);
                        System.out.println("子线程" + Thread.currentThread().getName() + "执行完毕");
                        latch.countDown();

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                ;
            }.start();
        }

        try {

            System.out.println("等待" + threadNum + "个子线程执行完毕...");
            latch.await();
            System.out.println(threadNum + "个子线程已经执行完毕");
            System.out.println("继续执行主线程");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
