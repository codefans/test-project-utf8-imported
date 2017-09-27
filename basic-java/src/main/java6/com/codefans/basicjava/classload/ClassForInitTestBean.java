package com.codefans.basicjava.classload;

/**
 * @author: caishengzhi
 * @date: 2017-09-27 17:01
 **/
public class ClassForInitTestBean {

    public static int value = 1;

    static {
        System.out.println("static block init...");
        value += 10;
        System.out.println("value+=10;finish. value=" + value);
    }

    public int getValue() {
        return value;
    }

}
