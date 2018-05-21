package com.codefans.basicjava.algorithm.timeout;

import java.util.Date;
import java.util.concurrent.*;

/**
 * @Author: ShengzhiCai
 * @Date: 2018-05-21 21:04
 */

public class FutureTimeout {

    public static void main(String[] args) {
        FutureTimeout futureTimeout = new FutureTimeout();
        futureTimeout.timeoutTest();
    }

    public void timeoutTest() {

        Callable<String> task = new Callable<String>() {
            @Override
            public String call() throws Exception {

                //暂停10s
//                Thread.sleep(10 * 1000);
                long timeout = 10 * 1000;
                long start = System.currentTimeMillis();
                while(true) {

                    if(System.currentTimeMillis() - start > timeout) {
                        break;
                    }
                    Thread.sleep(1000);
                    System.out.println(new Date());

                }

                return "resultString";
            }
        };

        try {


            ExecutorService executorService = Executors.newSingleThreadExecutor();
            Future<String> resultFuture = executorService.submit(task);
            long timeout = 5 * 1000;
            //5秒后只是线程返回,任务方法还在执行
            String result = resultFuture.get(timeout, TimeUnit.MILLISECONDS);
            System.out.println("result:" + result);



        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }


    }

}
