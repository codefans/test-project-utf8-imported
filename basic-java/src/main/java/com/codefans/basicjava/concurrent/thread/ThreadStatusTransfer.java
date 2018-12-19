package com.codefans.basicjava.concurrent.thread;

/**
 * @Author: codefans
 * @Date: 2018-12-19 22:30
 * Thread的方法：
 *      start(): 使该线程开始执行；,
 *      sleep(): 在指定的毫秒数内让当前正在执行的线程休眠（暂停执行），此操作受到系统计时器和调度程序精度和准确性的影响。,
 *      join(): 等待该线程终止。,
 *      interrupt(): 中断线程,
 *      yield(): 暂停当前正在执行的线程对象，并执行其他线程。
 * Object的方法：
 *      notify(): 唤醒在此对象监视器上等待的单个线程。,
 *      notifyAll(): 唤醒在此对象监视器上等待的所有线程。,
 *      wait(): 在其他线程调用此对象的 notify() 方法或 notifyAll() 方法前，导致当前线程等待。
 *
 */

public class ThreadStatusTransfer {

    public static void main(String[] args) {
        ThreadStatusTransfer threadStatusTransfer = new ThreadStatusTransfer();
//        threadStatusTransfer.joinTest();
        threadStatusTransfer.yieldTest();
    }

    public void joinTest() {

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("thread run() method...");
                    Thread.sleep(5 * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"thread01");
        t.start();

        try {
            System.out.println("before join:");
            long beginTime = System.currentTimeMillis();
            //等待线程t终止。前提是:线程t已经调用start方法启动了。
            t.join();
            long endTime = System.currentTimeMillis();
            System.out.println("after join:" + (endTime - beginTime)/1000 + "s");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }


    public void yieldTest() {

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("thread run() method...");
                    Thread.sleep(5 * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"thread01");
        t.start();

        try {
            System.out.println("before yield:");
            long beginTime = System.currentTimeMillis();
            //暂停当前正在执行的线程对象，并执行其他线程。
            t.yield();
            long endTime = System.currentTimeMillis();
            System.out.println("after yield:" + (endTime - beginTime)/1000 + "s");


        } catch (Exception e) {
            e.printStackTrace();
        }


    }



}
