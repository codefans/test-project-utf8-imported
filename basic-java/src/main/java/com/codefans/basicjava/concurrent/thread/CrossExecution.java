package com.codefans.basicjava.concurrent.thread;

import java.util.Date;

/**
 * @author:
 * @date: 2018-12-17 16:20:01
 * 两个线程交叉执行
 *
 * 参考资料：
 * 两个线程严格交替执行java实现
 * https://blog.csdn.net/springlustre/article/details/79450754
 */
public class CrossExecution {

    private static boolean isRunning = true;
    private static Object lock = new Object();

    public static void main(String[] args) {
        CrossExecution crossExecution = new CrossExecution();
        crossExecution.runCode();
    }

    public void runCode() {

        new Thread01().start();
        new Thread02().start();

    }

    class Thread01 extends Thread {
        
    }

    class Thread02 extends Thread {

    }

}
