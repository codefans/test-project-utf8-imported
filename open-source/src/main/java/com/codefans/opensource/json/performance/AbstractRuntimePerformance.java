package com.codefans.opensource.json.performance;

/**
 * @author caishengzhi
 * @date 2018/2/8 21:35
 */
public abstract class AbstractRuntimePerformance {

    public void runTime() {
        long startTime = System.currentTimeMillis();

        this.execute();

        long endTime = System.currentTimeMillis();

    }

    public abstract void execute();

}
