package com.codefans.interviewcase.concurrent;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @Author: ShengzhiCai
 * @Date: 2017-09-14 22:28
 */

public class CatchThreadException {

    public static void main(String[] args) {
        CatchThreadException cte = new CatchThreadException();
//        cte.tryCatchThreadExceptionTest();
        cte.catchThreadExceptionByFutureTest();
    }

    public void tryCatchThreadExceptionTest() {

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
