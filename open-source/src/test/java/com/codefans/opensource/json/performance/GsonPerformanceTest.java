package com.codefans.opensource.json.performance;

import org.junit.Test;

/**
 * @author: caishengzhi
 * @date: 2018-05-23 09:32
 */
public class GsonPerformanceTest {

    @Test
    public void gsonTest() {

        GsonPerformance gsonPerformance = new GsonPerformance();
        gsonPerformance.runTime();

    }

}
