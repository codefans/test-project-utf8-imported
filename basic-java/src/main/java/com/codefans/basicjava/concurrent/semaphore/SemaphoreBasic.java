package com.codefans.basicjava.concurrent.semaphore;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.Semaphore;

/**
 * @author: ShengzhiCai
 * @date: 2018-10-10 10:12
 * 控制工作线程的数量, 线程数到达最大值后, 必须退出一个, 才能加入一个
 *
 * 假若一个工厂有5台机器，但是有8个工人，一台机器同时只能被一个工人使用，只有使用完了，其他工人才能继续使用。
 * 那么我们就可以通过Semaphore来实现
 *
 */
public class SemaphoreBasic {

    public static void main(String[] args) {
        SemaphoreBasic semaphoreBasic = new SemaphoreBasic();
        semaphoreBasic.basicFunc();
    }

    public void basicFunc() {
        int N = 8;            //工人数
        final Semaphore semaphore = new Semaphore(3); //机器数目

        for(int i=0;i<N;i++) {
            final int num = i;
            new Thread(new Runnable() {
                @Override
                public void run() {

                    try {
                        semaphore.acquire();
                        System.out.println("工人" + num + "占用一个机器在生产...");
                        Thread.sleep(2000);
                        System.out.println("工人" + num + "释放出机器");
                        semaphore.release();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }

            }).start();
        }

    }


}
