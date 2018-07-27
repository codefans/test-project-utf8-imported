package com.codefans.reusablecode.bean;

import org.springframework.beans.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

/**
 * @Author: codefans
 * @Date: 2018-07-17 22:22
 */

public class SpringBeanUtils {

    public static void main(String[] args) {
        SpringBeanUtils springBeanUtils = new SpringBeanUtils();
        springBeanUtils.copyProperties();
    }

    public void copyProperties() {

        SourceBean sourceBean = new SourceBean();
        sourceBean.setaByte((byte)1);
        sourceBean.setByteObj(new Byte("2"));
        sourceBean.setBooleanObj(true);
        sourceBean.setStr("hello world");
        sourceBean.setDate(new Date());
        sourceBean.setBigDecimal(new BigDecimal("2.34"));
        sourceBean.setBigInteger(new BigInteger("1234567898754"));

        DestBean destBean = new DestBean();
        BeanUtils.copyProperties(sourceBean, destBean);
        System.out.println("aByte:" + destBean.getaByte());
        System.out.println("ByteObj:" + destBean.getByteObj());
        System.out.println("aBoolean:" + destBean.isaBoolean());
        System.out.println("Boolean:" + destBean.getBooleanObj());
        System.out.println("str:" + destBean.getStr());
        System.out.println("date:" + destBean.getDate());
        System.out.println("BigDecimal:" + destBean.getBigDecimal());
        System.out.println("BigInteger:" + destBean.getBigInteger());



    }





}
