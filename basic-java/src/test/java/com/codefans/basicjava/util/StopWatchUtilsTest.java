package com.codefans.basicjava.util;

import org.junit.Test;

/**
 * @author: ShengzhiCai
 * @date: 2018-06-21 15:25
 */
public class StopWatchUtilsTest {


    @Test
    public void test() {

        StopWatchUtils.start();
        try {
            Thread.sleep(3 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        StopWatchUtils.stop();

        System.out.println(StopWatchUtils.getTimeInMills());
        System.out.println(StopWatchUtils.getTimeInSeconds());

        //重新开始前必须reset,否则会报错
        StopWatchUtils.reset();

        StopWatchUtils.start();
        try {
            Thread.sleep(2 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        StopWatchUtils.stop();

        System.out.println(StopWatchUtils.getTimeInMills());
        System.out.println(StopWatchUtils.getTimeInSeconds());

    }

}
