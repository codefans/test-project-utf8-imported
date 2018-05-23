package com.codefans.opensource.json.performance;

import com.codefans.opensource.json.JacksonJsonUtils;

import java.io.Serializable;

/**
 * @author: codefans
 * @date: 2018-05-23 08:58
 */
public class JacksonPerformance extends AbstractRuntimePerformance {


    @Override
    public void execute() {

        String jsonStr = JacksonJsonUtils.writeValue(domain);
        System.out.println("jsonStr:" + jsonStr);

    }



}
