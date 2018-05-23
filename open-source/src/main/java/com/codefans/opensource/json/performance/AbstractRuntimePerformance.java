package com.codefans.opensource.json.performance;

/**
 * @author caishengzhi
 * @date 2018/2/8 21:35
 */
public abstract class AbstractRuntimePerformance {

    PerformanceDomain domain;

    public AbstractRuntimePerformance() {

        domain = new PerformanceDomain();
        domain.setId("111111");
        domain.setType("json performance");
        domain.setRuntime(1234);


    }


    public void runTime() {
        long startTime = System.currentTimeMillis();

        this.execute();

        long endTime = System.currentTimeMillis();

        System.out.println("cost time:[" + (endTime - startTime) / 1000 + "s]");
    }

    public abstract void execute();

}
