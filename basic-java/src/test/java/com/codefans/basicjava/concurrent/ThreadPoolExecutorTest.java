package com.codefans.basicjava.concurrent;

import com.codefans.basicjava.concurrent.threadpool.NamedThreadFactory;
import com.codefans.basicjava.concurrent.threadpool.ParamRunnable;
import com.codefans.basicjava.dto.ResultDto;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @Author: ShengzhiCai
 * @Date: 2017-09-29 14:51
 */

public class ThreadPoolExecutorTest {

    private int COUNT_BITS = Integer.SIZE - 3;
    private int CAPACITY   = (1 << COUNT_BITS) - 1;
    private int RUNNING    = -1 << COUNT_BITS;
    private final int SHUTDOWN   =  0 << COUNT_BITS;
    private final int STOP       =  1 << COUNT_BITS;

    @Test
    public void sourceCodeTest() {


        System.out.println("COUNT_BITS:" + COUNT_BITS);
        System.out.println("CAPACITY:" + CAPACITY);
        System.out.println("~CAPACITY:" + ~CAPACITY);
        System.out.println("RUNNING:" + RUNNING);
        System.out.println("SHUTDOWN:" + SHUTDOWN);
        System.out.println("STOP:" + STOP);

        int runState = this.runStateOf(2);
        System.out.println("runState:" + runState);

        int workerCount = this.workerCountOf(2);
        System.out.println("workerCount:" + workerCount);
        System.out.println("c/capacity:" + 2%CAPACITY);

    }

    @Test
    public void threadPoolExecutorTest() {

        ThreadPoolExecutor poolExecutor = null;

        try {
//        ThreadPoolExecutor(
// int corePoolSize,
// int maximumPoolSize,
// long keepAliveTime,
// TimeUnit unit,
// BlockingQueue<Runnable> workQueue,
// ThreadFactory threadFactory,
// RejectedExecutionHandler handler)
//        用给定的初始参数创建新的 ThreadPoolExecutor。

            /**
             * 核心线程数
             * 如果运行的线程少于 corePoolSize，则创建新线程来处理请求，即使其他辅助线程是空闲的
             */
            int corePoolSize = 2;
            /**
             * 最大线程数
             * 如果运行的线程多于 corePoolSize 而少于 maximumPoolSize，则仅当队列满时才创建新线程
             * 如果设置的 corePoolSize 和 maximumPoolSize 相同，则创建了固定大小的线程池
             */
            int maximumPoolSize = 4;
            /**
             * 保持活动时间
             * 如果池中当前有多于 corePoolSize 的线程，则这些多出的线程在空闲时间超过 keepAliveTime 时将会终止（参见 getKeepAliveTime(java.util.concurrent.TimeUnit)）。
             * 这提供了当池处于非活动状态时减少资源消耗的方法。
             * 如果池后来变得更为活动，则可以创建新的线程。
             * 也可以使用方法 setKeepAliveTime(long, java.util.concurrent.TimeUnit) 动态地更改此参数。
             * 使用 Long.MAX_VALUE TimeUnit.NANOSECONDS 的值在关闭前有效地从以前的终止状态禁用空闲线程。
             * 默认情况下，保持活动策略只在有多于 corePoolSizeThreads 的线程时应用。
             * 但是只要 keepAliveTime 值非 0，allowCoreThreadTimeOut(boolean) 方法也可将此超时策略应用于核心线程。
             */
            long keepAliveTime = 100L;
            /**
             * keepAliveTime 参数的时间单位
             */
            TimeUnit unit = TimeUnit.SECONDS;
            /**
             * 用于保存任务的队列,当任务数大于corePoolSize时，任务会被放到队列中
             */
            BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<Runnable>(30);
            /**
             * 创建新线程
             * 使用 ThreadFactory 创建新线程。通过提供不同的 ThreadFactory，可以改变线程的名称、线程组、优先级、守护进程状态，等等
             */
            ThreadFactory threadFactory = new NamedThreadFactory("thread_pool_executor");
            /**
             * 当提交的任务数大于(最大线程数与阻塞队列的大小之和)时,才会丢弃任务。
             * 注意：这里的任务数是指当前未处理的任务数, 而不是提交的总任务数, 如果提交任务的速度小于任务处理的速度, 那么提交的总任务数大于(最大线程数与阻塞队列的大小之和)时, 也不会丢弃任务的
             * 在默认的 ThreadPoolExecutor.AbortPolicy 中，处理程序遭到拒绝将抛出运行时 RejectedExecutionException。
             * 在 ThreadPoolExecutor.CallerRunsPolicy 中，线程调用运行该任务的 execute 本身。此策略提供简单的反馈控制机制，能够减缓新任务的提交速度。
             * 在 ThreadPoolExecutor.DiscardPolicy 中，不能执行的任务将被删除。
             * 在 ThreadPoolExecutor.DiscardOldestPolicy 中，如果执行程序尚未关闭，则位于工作队列头部的任务将被删除，然后重试执行程序（如果再次失败，则重复此过程）。
             */

            RejectedExecutionHandler handler = new ThreadPoolExecutor.AbortPolicy();

            poolExecutor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
            int taskNums = 10;
            ResultDto result = null;
            List<Future<ResultDto>> resList = new ArrayList<Future<ResultDto>>();
            int poolSize = 0;

            for(int i = 0; i < taskNums; i ++) {
                try {
                    result = new ResultDto();
//                    Future<ResultDto> future = poolExecutor.submit(new ParamRunnable(result), result);
//                    resList.add(future);

                    poolExecutor.execute(new ParamRunnable(result));

                    poolSize = poolExecutor.getPoolSize();
                    System.out.println("poolSize:" + poolSize);

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

//        try {
//            Thread.sleep(10 * 1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

            for(int i = 0; i < resList.size(); i ++) {
                Future<ResultDto> future = resList.get(i);
                ResultDto resultDto = future.get();
                System.out.println(resultDto.getName() + ", time:" + resultDto.getDate());

            }



        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } finally {
            poolExecutor.shutdown();
        }


    }

    private int workerCountOf(int c)  {
        return c & CAPACITY;
    }

    private int runStateOf(int c) {
        return c & ~CAPACITY;
    }

    private boolean isRunning(int c) {
        return c < SHUTDOWN;
    }

}
