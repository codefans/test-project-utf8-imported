package com.codefans.basicjava.generics;

import org.junit.Test;

import java.util.ArrayList;

/**
 * @author: caishengzhi
 * @date: 2018-05-31 17:20
 */
public class GenericsConstructorTest {

    @Test
    public void constructorTest() {

        String result = "SUCCESS";
        String message = "错误信息。。。";
        int code = 1000;
        GenericsMessage gmsg = new GenericsMessage(result, code, message);
        System.out.println(gmsg.getMessage());

        String newMsg = "正常信息.....";
        gmsg = this.paramCheckFail(newMsg);
        System.out.println(gmsg.getMessage());

        //泛型类型擦除
        Class c1 = new ArrayList<String>().getClass();
        Class c2 = new ArrayList<Integer>().getClass();
        System.out.println(c1 == c2); // true


    }

    public <D> GenericsMessage<D> paramCheckFail(String msg) {

//        GenericsMessage(String result, int code, D data)
//        return new GenericsMessage("SUCCESS", 1000, msg);

        //GenericsMessage(String result, Integer code,String message)
        return new GenericsMessage<D>("SUCCESS", 1000, msg);
    }

}
