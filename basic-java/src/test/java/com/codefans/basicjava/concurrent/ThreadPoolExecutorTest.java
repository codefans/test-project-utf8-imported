package com.codefans.basicjava.concurrent;

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
    public void test() {

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

            int corePoolSize = 10;
            int maximumPoolSize = 20;
            long keepAliveTime = 100L;
            TimeUnit unit = TimeUnit.SECONDS;
            BlockingQueue<Runnable> workQueue = new LinkedBlockingDeque<Runnable>();
            ThreadFactory threadFactory = new NamedThreadFactory("thread_pool_executor");
            RejectedExecutionHandler handler = new ThreadPoolExecutor.AbortPolicy();

            poolExecutor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
            int taskNums = 100;
            ResultDto result = null;
            List<Future<ResultDto>> resList = new ArrayList<Future<ResultDto>>();

            for(int i = 0; i < taskNums; i ++) {
                result = new ResultDto();
                Future<ResultDto> future = poolExecutor.submit(new ParamRunnable(result), result);
                resList.add(future);

            }

//        try {
//            Thread.sleep(10 * 1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

            for(int i = 0; i < taskNums; i ++) {
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
