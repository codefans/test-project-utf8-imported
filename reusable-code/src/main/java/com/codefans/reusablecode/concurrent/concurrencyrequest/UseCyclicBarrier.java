package com.codefans.reusablecode.concurrent.concurrencyrequest;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author: ShengzhiCai
 * @date: 2018-10-10 09:20
 *
 * 出处：
 * Java中如何模拟真正的同时并发请求？
 * http://www.importnew.com/30073.html
 *
 */
public class UseCyclicBarrier {

    public static void main(String[] args) throws InterruptedException {

        Runnable taskTemp = new Runnable() {

            private int iCounter;

            @Override
            public void run() {
                // 发起请求
                String result = HttpUrlConnectionUtils.doGet("https://www.baidu.com/");
                if(result != null && result.trim().length() > 0) {
                    System.out.println("访问成功, 返回内容为: " + result);
                }
                iCounter++;
                System.out.println(System.nanoTime() + " [" + Thread.currentThread().getName() + "] iCounter = " + iCounter);
            }
        };

        UseCyclicBarrier latchTest = new UseCyclicBarrier();
        latchTest.startNThreadsByBarrier(5, taskTemp);
    }

    public void startNThreadsByBarrier(int threadNums, Runnable finishTask) throws InterruptedException {
        // 设置栅栏解除时的动作，比如初始化某些值
        CyclicBarrier barrier = new CyclicBarrier(threadNums);
        // 启动 n 个线程，与栅栏阀值一致，即当线程准备数达到要求时，栅栏刚好开启，从而达到统一控制效果
        for (int i = 0; i < threadNums; i++) {
            Thread.sleep(100);
            new Thread(new CounterTask(barrier, finishTask)).start();
        }
        System.out.println(Thread.currentThread().getName() + " out over...");
    }

}

class CounterTask implements Runnable {

    /**
     * 传入栅栏，一般考虑更优雅方式
     */
    private CyclicBarrier barrier;

    private Runnable task;

    public CounterTask(final CyclicBarrier barrier, final Runnable task) {
        this.barrier = barrier;
        this.task = task;
    }

    public void run() {
        System.out.println(Thread.currentThread().getName() + " - " + System.currentTimeMillis() + " is ready...");
        try {
            // 设置栅栏，使在此等待，到达位置的线程达到要求即可开启大门
            barrier.await();
            task.run();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " - " + System.currentTimeMillis() + " started...");
    }
}
