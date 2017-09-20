package com.codefans.interviewcase.concurrent;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @Author: ShengzhiCai
 * @Date: 2017-09-14 22:28
 * 线程异常捕获问题测试类
 */

public class CatchThreadException {

    public static void main(String[] args) {
        CatchThreadException cte = new CatchThreadException();
//        cte.cannotCatchThreadExceptionTest();
        cte.catchThreadExceptionTest();
//        cte.catchThreadExceptionByFutureTest();
    }

    /**
     * 正常try-catch捕获不到线程抛出的异常
     */
    public void cannotCatchThreadExceptionTest() {

        try {
            new Thread(new Runnable() {

                @Override
                public void run() {

                    System.out.println("running in thread...");
                    throw new RuntimeException("thread exception try catch test");


                }

            }).start();
        } catch (Exception e) {
            System.out.println("catched");
            e.printStackTrace();
        }

    }

    /**
     * 通过Thread.setUncaughtExceptionHandler方法捕获线程抛出的异常
     */
    public void catchThreadExceptionTest() {
        int nThreads = 3;
        Thread t = null;
        for(int i = 0; i < nThreads; i ++ ) {
            try {


                final int index = i;
                t = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println(index);
                        throw new RuntimeException("第" + index + "个线程抛异常...");
                    }
                });

                t.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
                    @Override
                    public void uncaughtException(Thread t, Throwable e) {
                        System.out.println("aaa" + e.getMessage());
                    }
                });

                t.start();


            } catch (Exception e) {
                System.out.println("executionEx");
                e.printStackTrace();
            }


        }



    }

    /**
     * 通过Future获取线程异常
     */
    public void catchThreadExceptionByFutureTest() {
        int nThreads = 3;
        ExecutorService executorService = Executors.newFixedThreadPool(nThreads);
        for(int i = 0; i < nThreads; i ++ ) {
            try {



                Future future = executorService.submit(new Runnable() {
                    @Override
                    public void run() {

                        System.out.println("running in catchThreadExceptionByFutureTest...");
                        throw new RuntimeException("try catch thread exception by future");

                    }
                });
                System.out.println("exception from thread begin: ");
                System.out.println(future.get());
                System.out.println("exception from thread end. ");



            } catch (InterruptedException e) {
                System.out.println("Interrup....");
                e.printStackTrace();
            } catch (ExecutionException e) {
                System.out.println("executionEx");
                e.printStackTrace();
            }


        }

        executorService.shutdown();


    }

}
