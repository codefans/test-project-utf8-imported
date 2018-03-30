package com.codefans.basicjava.concurrent;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by codefans on 2018/3/30.
 */
public class ThreadJoinTest {

    /**
     * 这个例子中, t1永远比t2先运行完
     */
    @Test
    public void oneThreadRunAfterAnother() {

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1 * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("thread1 running, time:" + new Date());

            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1 * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("thread2 running, time:" + new Date());
            }
        });

        try {
            t1.start();
            //join()方法主要是让调用改方法的thread完成run方法里面的东西后，在执行join()方法后面的代码
            t1.join();
            t2.start();

            try {
                //这里如果不睡2秒，主线程结束，t2也退出了，只能看到t1的输出结果
                Thread.sleep(2 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    /**
     * 测试模拟高并发的例子
     */
    @Test
    public void joinTest() {

        try {

            int threadNums = 5000;
            List<JoinThread> threadList = new ArrayList<JoinThread>();
            JoinThread joinThread = null;
            System.out.println("thread starting, time:" + new Date());
            for(int i = 0; i < threadNums; i ++) {

                joinThread = new JoinThread("JoinThread_" + (i + 1));
                joinThread.start();

                threadList.add(joinThread);

            }
            System.out.println("thread already start, time:" + new Date());

            System.out.println("thread joining, time:" + new Date());
            for(int i = 0; i < threadNums; i ++) {
//                System.out.println("No." + (i + 1) + " thread join, time:" + new Date());
                //虽然threadList中的线程是按顺序加入的，但是执行的结果不是按顺序输出的，
                //因为start之后线程就进入就绪状态(Runnable状态)，所有就绪状态的线程，执行顺序是随机的，因为CPU调度是随机的。
                joinThread = threadList.get(i);
                joinThread.join();

            }
            System.out.println("thread all join, time:" + new Date());


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * join()方法主要是让调用该方法的thread完成run方法里面的东西后，再执行join()方法后面的代码
     * 通过类似下面这个例子，可以实现其他线程间交替执行的功能
     */
    @Test
    public void runThreadOneByOne() {
        try {

            Thread currentThread = null;
            for (int i = 0; i <10; i++) {
                //每个线程拥有前一个线程的引用，需要等待前一个线程终止，才能从等待中返回
                Thread thread = new Thread(new Domino(currentThread),String.valueOf(i));
                thread.start();
                currentThread=thread;
            }
            TimeUnit.SECONDS.sleep(10);
            System.out.println(Thread.currentThread().getName()+"，执行结束！");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static class Domino implements Runnable{
        private Thread thread;
        public  Domino(Thread thread) {
            this.thread=thread;
        }
        @Override
        public void run() {

            try {
                if(thread != null) {
                    //join()方法主要是让调用该方法的thread完成run方法里面的东西后，再执行join()方法后面的代码
                    thread.join();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"执行结束！");

        }

    }



}

