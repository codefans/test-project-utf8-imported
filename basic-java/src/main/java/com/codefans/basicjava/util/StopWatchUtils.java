package com.codefans.basicjava.util;

import org.apache.commons.lang.time.StopWatch;

/**
 * @author: ShengzhiCai
 * @date: 2018-06-21 15:22
 */
public class StopWatchUtils {

    private static StopWatch stopWatch = new StopWatch();

    public static void start() {
        stopWatch.start();
    }

    public static void stop() {
        stopWatch.stop();
    }

    public static void reset() {
        stopWatch.reset();
    }

    public static long getTimeInMills() {
        return stopWatch.getTime();
    }

    public static long getTimeInSeconds() {
        return (stopWatch.getTime() / 1000);
    }

}
