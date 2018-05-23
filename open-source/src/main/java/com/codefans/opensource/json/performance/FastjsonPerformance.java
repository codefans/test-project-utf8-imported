package com.codefans.opensource.json.performance;

import com.alibaba.fastjson.JSON;

/**
 * @author: codefans
 * @date: 2018-05-23 08:58
 */
public class FastjsonPerformance extends AbstractRuntimePerformance {

    @Override
    public void execute() {

        PerformanceDomain domain = new PerformanceDomain();

        System.out.println(JSON.toJSON(domain));

    }


}
