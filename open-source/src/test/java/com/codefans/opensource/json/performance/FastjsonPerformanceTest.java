package com.codefans.opensource.json.performance;

import org.junit.Test;

/**
 * @author: codefans
 * @date: 2018-05-23 09:00
 */
public class FastjsonPerformanceTest {

    @Test
    public void fastjsonTest() {

        FastjsonPerformance fastjsonPerformance = new FastjsonPerformance();
        fastjsonPerformance.runTime();

    }

}
