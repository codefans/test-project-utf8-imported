package com.codefans.basicjava.lang.util;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: caishengzhi
 * @date: 2017-10-17 15:10
 *
 * 执行命令：
 * jps
 * jstack pid
 *
 **/
public class HashMapThread extends Thread {

    private static AtomicInteger ai = new AtomicInteger(0);
    private static Map<Integer, Integer> map = new HashMap<Integer, Integer>(1);

    public void run()
    {
        while (ai.get() < 100000)
        {
            map.put(ai.get(), ai.get());
            ai.incrementAndGet();
        }
    }

    public static void main(String[] args) {
        HashMapThread hmt0 = new HashMapThread();
        HashMapThread hmt1 = new HashMapThread();
        HashMapThread hmt2 = new HashMapThread();
        HashMapThread hmt3 = new HashMapThread();
        HashMapThread hmt4 = new HashMapThread();
        hmt0.start();
        hmt1.start();
        hmt2.start();
        hmt3.start();
        hmt4.start();
    }


}
