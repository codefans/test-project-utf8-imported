package com.codefans.basicjava.concurrent;

import com.codefans.basicjava.dto.ResultDto;
import org.junit.Test;

import javax.xml.transform.Result;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @Author: ShengzhiCai
 * @Date: 2017-09-29 14:58
 */

public class ExecutorServiceTest {


    /**
     *
     static ExecutorService newCachedThreadPool()
     创建一个可根据需要创建新线程的线程池，但是在以前构造的线程可用时将重用它们。
     static ExecutorService newCachedThreadPool(ThreadFactory threadFactory)
     创建一个可根据需要创建新线程的线程池，但是在以前构造的线程可用时将重用它们，并在需要时使用提供的 ThreadFactory 创建新线程。

     */
    @Test
    public void newCachedThreadPoolTest() throws ExecutionException, InterruptedException {

//        ExecutorService exeService = Executors.newCachedThreadPool();
        ExecutorService exeService = Executors.newCachedThreadPool(new NamedThreadFactory("cached_thread_pool"));
        int taskNums = 100;
        ResultDto result = null;
        List<Future<ResultDto>> resList = new ArrayList<Future<ResultDto>>();

        for(int i = 0; i < taskNums; i ++) {
            result = new ResultDto();
            Future<ResultDto> future = exeService.submit(new ParamRunnable(result), result);
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

        exeService.shutdown();

    }

    /**
     static ExecutorService newFixedThreadPool(int nThreads)
     创建一个可重用固定线程数的线程池，以共享的无界队列方式来运行这些线程。
     static ExecutorService newFixedThreadPool(int nThreads, ThreadFactory threadFactory)
     创建一个可重用固定线程数的线程池，以共享的无界队列方式来运行这些线程，在需要时使用提供的 ThreadFactory 创建新线程。

     */
    public void newFixedThreadPoolTest() {

//        ExecutorService executorService = Executors.newFixedThreadPool(10);
        ExecutorService executorService = Executors.newFixedThreadPool(10, new NamedThreadFactory("fixed_thread_pool"));



    }

    /**
     static ScheduledExecutorService newScheduledThreadPool(int corePoolSize)
     创建一个线程池，它可安排在给定延迟后运行命令或者定期地执行。
     static ScheduledExecutorService newScheduledThreadPool(int corePoolSize, ThreadFactory threadFactory)
     创建一个线程池，它可安排在给定延迟后运行命令或者定期地执行。

     */
    public void newScheduledThreadPoolTest() {

    }

    /**
     static ExecutorService newSingleThreadExecutor()
     创建一个使用单个 worker 线程的 Executor，以无界队列方式来运行该线程。
     static ExecutorService newSingleThreadExecutor(ThreadFactory threadFactory)
     创建一个使用单个 worker 线程的 Executor，以无界队列方式来运行该线程，并在需要时使用提供的 ThreadFactory 创建新线程。

     */
    public void newSingleThreadExecutorTest() {

    }

    /**
     static ScheduledExecutorService newSingleThreadScheduledExecutor()
     创建一个单线程执行程序，它可安排在给定延迟后运行命令或者定期地执行。
     static ScheduledExecutorService newSingleThreadScheduledExecutor(ThreadFactory threadFactory)
     创建一个单线程执行程序，它可安排在给定延迟后运行命令或者定期地执行。

     */
    public void newSingleThreadScheduledExecutorTest() {

    }

}
