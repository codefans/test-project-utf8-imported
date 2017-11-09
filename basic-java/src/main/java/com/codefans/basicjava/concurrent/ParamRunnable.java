package com.codefans.basicjava.concurrent;

import com.codefans.basicjava.dto.ResultDto;

import java.util.Date;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: ShengzhiCai
 * @Date: 2017-09-29 16:08
 */

public class ParamRunnable implements Runnable {

    private ResultDto resultDto;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public ParamRunnable(ResultDto resultDto) {
        this.resultDto = resultDto;
    }

    @Override
    public void run() {

        lock.lock();
        try {

            resultDto.setName(Thread.currentThread().getName());
//            Thread.sleep(2 * 1000);
            resultDto.setDate(new Date().toString());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }



}
