package com.codefans.basicjava.concurrent.threadpool;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @Author: ShengzhiCai
 * @Date: 2017-09-29 15:07
 */

public class NamedThreadFactory implements ThreadFactory {

    private String namePrefix;
    private AtomicLong threadNum = new AtomicLong(0);

    public NamedThreadFactory(String namePrefix) {
        this.namePrefix = namePrefix;
    }

    @Override
    public Thread newThread(Runnable r) {
        return new Thread(r, namePrefix + "_" + threadNum.getAndIncrement());
    }


}
