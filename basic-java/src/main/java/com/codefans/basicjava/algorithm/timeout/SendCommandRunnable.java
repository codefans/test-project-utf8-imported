package com.codefans.basicjava.algorithm.timeout;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: ShengzhiCai
 * @Date: 2018-05-22 6:25
 */

public class SendCommandRunnable  implements Runnable {
    String mat = null;
    String mReply = null;
    Lock mLock;
    Condition mCondition;
    boolean mIsSafeCard;

    public SendCommandRunnable(String at, Lock lock, Condition condition, boolean isSafeCard) {
        mat = at;
        mLock = lock;
        mCondition = condition;
        mIsSafeCard = isSafeCard;
    }

    public String getReply() {
        return mReply;
    }

    public Lock getLock() {
        return mLock;
    }
    public Condition getCondition() {
        return mCondition;
    }

    public void run() {
        //阻塞代码 read节点 读取数据到mReply
        mLock.lock();//持锁发那个字阻塞代码之后
        mCondition.signalAll();
        mLock.unlock();

    }

    public static void main(String[] args) {

        String at = "hello world";
        sendCommandSecureCardAndStick(at);

    }

    public static String sendCommandSecureCardAndStick(String at) {
        at = at.trim() + "\r\n";
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        SendCommandRunnable runnable = new SendCommandRunnable(at, lock, condition, false);
        new Thread(runnable, "thread—1").start();

        lock.lock();
        try {
            condition.await(5, TimeUnit.SECONDS);//主线程5秒等待
        } catch (InterruptedException e) {
        }
        lock.unlock();

        return runnable.getReply();//获取新线程读取节点的内容
    }

}
