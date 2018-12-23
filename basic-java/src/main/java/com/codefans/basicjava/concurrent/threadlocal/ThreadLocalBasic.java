package com.codefans.basicjava.concurrent.threadlocal;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author:
 * @date: 2018-12-17 16:18:57
 * ThreadLocal特性测试
 */
public class ThreadLocalBasic {

    private static ThreadLocal<Integer> numThreadLocal = new ThreadLocal<Integer>(){
        public Integer initialValue() {
            return new Integer(1);
        }
    };

    public static void main(String[] args) {
        ThreadLocalBasic threadLocalBasic = new ThreadLocalBasic();
        threadLocalBasic.startup();
    }

    public void startup() {
        ExecutorService newCachedThreadPool = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            newCachedThreadPool.execute(new NotSaftThread((i+1)));
        }
    }

    class NotSaftThread extends Thread {
        private int i;
        NotSaftThread(int i) {
            this.i = i;
        }
        @Override
        public void run() {
//            Integer num = numThreadLocal.get();
            numThreadLocal.set(i);

            System.out.println("name:" + Thread.currentThread().getName() + ", num:" + numThreadLocal.get());
        }
    }




}
