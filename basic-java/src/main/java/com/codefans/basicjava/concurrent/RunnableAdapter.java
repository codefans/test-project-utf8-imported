package com.codefans.basicjava.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;

/**
 * @Author: ShengzhiCai
 * @Date: 2018-05-23 7:07
 */

public class RunnableAdapter<V> {

    private Callable<V> callable;
    private V result;

    public RunnableAdapter(Runnable task, V result) {
        this.result = result;
        callable = Executors.callable(task, result);
    }

    public V call() throws Exception {
        return callable.call();
    }

}
