package com.codefans.basicjava.concurrent.cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author: ShengzhiCai
 * @date: 2018-10-10 10:12
 * CyclicBarrier一般用于一组线程互相等待至某个状态，然后这一组线程再同时执行；
 */
public class CyclicBarrierBasic {

    public static void main(String[] args) {

        CyclicBarrierBasic cyclicBarrierBasic = new CyclicBarrierBasic();
        cyclicBarrierBasic.basicFunc();

    }

    public void basicFunc() {

        final int taskNum = 4;
        final CyclicBarrier barrier  = new CyclicBarrier(taskNum);
        this.cyclicBarrierTask(barrier, taskNum);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("CyclicBarrier重用");

        //CyclicBarrier是可重用的, CountDownLatch不可重用
        this.cyclicBarrierTask(barrier, taskNum);

    }

    public void cyclicBarrierTask(final CyclicBarrier cyclicBarrier, int taskNum) {
        for(int i = 0 ; i < taskNum; i++)

            new Thread(new Runnable() {
                @Override
                public void run() {

                    String currentThreadName = Thread.currentThread().getName();

                    System.out.println("线程" + currentThreadName + "正在写入数据...");
                    try {
                        Thread.sleep(3000);//以睡眠来模拟写入数据操作
                        System.out.println("线程" + currentThreadName + "写入数据完毕，等待其他线程写入完毕");
                        cyclicBarrier.await();

                        //所有线程完成后继续后面的任务
                        System.out.println("所有线程写入完毕，继续处理线程" + currentThreadName + "的其他任务...");

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }catch(BrokenBarrierException e){
                        e.printStackTrace();
                    }

                }

            }).start();
    }

}
