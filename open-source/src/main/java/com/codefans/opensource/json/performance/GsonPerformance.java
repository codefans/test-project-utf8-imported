package com.codefans.opensource.json.performance;

import com.google.gson.Gson;

/**
 * @author: codefans
 * @date: 2018-05-23 08:59
 */
public class GsonPerformance extends AbstractRuntimePerformance {


    @Override
    public void execute() {

        Gson gson = new Gson();
        String jsonStr = gson.toJson(domain);
        System.out.println(jsonStr);


    }


}
