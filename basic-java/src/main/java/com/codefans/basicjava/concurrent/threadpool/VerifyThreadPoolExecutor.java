package com.codefans.basicjava.concurrent.threadpool;

import java.util.Date;
import java.util.concurrent.*;

/**
 * @Author: codefans
 * @Date: 2018-12-16 23:31
 */

public class VerifyThreadPoolExecutor {

    public static void main(String[] args) {
        VerifyThreadPoolExecutor verifyThreadPoolExecutor = new VerifyThreadPoolExecutor();
        verifyThreadPoolExecutor.verify();
    }

    public void verify() {

        //验证核心线程数
//        this.verifyCorePoolSize();

        //验证最大线程数
        this.verifyMaximumPoolSize();

    }

    /**
     * corePoolSize-核心线程数
     * 如果待处理的任务数少于 corePoolSize，则创建新线程来处理请求，即使其他辅助线程是空闲的。
     * 验证方法：
     *    (1)降低任务提交速度，让下一个任务提交时，上一个任务已经执行完，看看此时线程数是多少？是否是每提交一个任务创建一个新线程？
     *      结果：是每提交一个任务，新建一个线程。
     *    (2)降低线程执行速度，让下一个任务提交时，上一个线程还没有执行完，看看此时线程数是否会增加到corePoolSize？
     *      结果：线程数增加到corePoolSize个了。
     * 结论：
     *    前corePoolSize个任务，每提交一个任务，新建一个线程。
     */
    public void verifyCorePoolSize() {

        ThreadPoolExecutor poolExecutor = null;

        try {
            int corePoolSize = 3;
            int maximumPoolSize = 6;
            long keepAliveTime = 100L;
            TimeUnit unit = TimeUnit.SECONDS;
            BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<Runnable>(30);
            ThreadFactory threadFactory = new NamedThreadFactory("thread_pool_executor");
            RejectedExecutionHandler handler = new ThreadPoolExecutor.AbortPolicy();
            poolExecutor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
            int taskNums = 10;
            int currentPoolSize = 0;

            for(int i = 0; i < taskNums; i ++) {
                try {
                    poolExecutor.execute(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                //降低线程执行速度
                                Thread.sleep(3 * 1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            System.out.println(Thread.currentThread().getName() + ", " + new Date());
                        }
                    });

                    currentPoolSize = poolExecutor.getPoolSize();
                    System.out.println("currentPoolSize:" + currentPoolSize);

                    //降低任务提交速度
                    Thread.sleep(3 * 1000);

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }



        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            poolExecutor.shutdown();
        }

    }

    /**
     * 目标：
     *    验证第(corePoolSize+1)个线程是否是队列满之后才创建的。
     * 验证方法：
     *    (1)降低线程执行速度, 看是否是在提交第(corePoolSize+队列长度+1)个任务时,创建第(corePoolSize+1)个线程.
     * 如本例中核心线程数是3,最大线程数是6,队列长度是4,那就是看是否在提交第8个任务时,创建第4个线程？
     * 结论：
     *    确实是在提交第8个任务时,创建的第4个线程。即只有队列满了之后,才会去创建(maximumPoolSize-corePoolSize)这部分线程。
     */
    public void verifyMaximumPoolSize() {

        ThreadPoolExecutor poolExecutor = null;

        try {
            int corePoolSize = 3;
            int maximumPoolSize = 6;
            long keepAliveTime = 100L;
            TimeUnit unit = TimeUnit.SECONDS;
            BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<Runnable>(4);
            ThreadFactory threadFactory = new NamedThreadFactory("thread_pool_executor");
            RejectedExecutionHandler handler = new ThreadPoolExecutor.AbortPolicy();
            poolExecutor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
            int taskNums = 10;
            int currentPoolSize = 0;

            for(int i = 0; i < taskNums; i ++) {
                try {
                    poolExecutor.execute(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                //降低线程执行速度
                                Thread.sleep(3 * 1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            System.out.println(Thread.currentThread().getName() + ", " + new Date());
                        }
                    });

                    currentPoolSize = poolExecutor.getPoolSize();
                    System.out.println("task [No." + (i+1) + "], currentPoolSize:" + currentPoolSize);

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }



        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            poolExecutor.shutdown();
        }

    }



}
