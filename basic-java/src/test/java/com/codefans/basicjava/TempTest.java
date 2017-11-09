package com.codefans.basicjava;

import org.junit.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.LockSupport;

/**
 * @author: caishengzhi
 * @date: 2017-10-31 9:36
 **/
public class TempTest {

    @Test
    public void tempTest() {

        Map<String, String> map = new HashMap<String, String>();
        map.put("111", "111");
        map.put("222", "222");
        map.put("333", "333");
        System.out.println(map.toString());


        System.out.println(new Date());

        LockSupport.parkNanos(999999999);

        System.out.println(new Date());


    }

}
