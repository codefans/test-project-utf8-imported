package com.codefans.basicjava.concurrent;

import org.junit.Test;

/**
 * @Author: ShengzhiCai
 * @Date: 2018-05-23 7:10
 */

public class RunnableAdapterTest {

    @Test
    public void callableAdapterTest() {

        try {
            Runnable task = new Runnable() {
                @Override
                public void run() {

                    try {
                        Thread.sleep(3 * 1000);
                        System.out.println("running....");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };

            String result = "";

            RunnableAdapter<String> callableAdapter = new RunnableAdapter(task, result);
            String resultStr = callableAdapter.call();

            System.out.println("resultStr:" + resultStr);


        } catch (Exception e) {
            e.printStackTrace();
        }

    }





}
