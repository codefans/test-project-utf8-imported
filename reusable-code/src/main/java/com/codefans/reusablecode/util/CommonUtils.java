package com.codefans.reusablecode.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: codefans
 * @date: 2018-07-27 14:15
 */
public class CommonUtils {

    public static Map<String, Object> bean2map(Object bean) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Map<String, Object> map = new HashMap<String, Object>();

        Class beanCls = bean.getClass();
        Field[] declaredFields = beanCls.getDeclaredFields();
        Field[] fields = beanCls.getFields();

//        System.out.println("declaredFields.length=" + declaredFields.length);
//        System.out.println("fields.length=" + fields.length);
//        System.out.println("(declaredFields.length == fields.length)=" + (declaredFields.length == fields.length));

        Field field = null;
        String fieldName = "";
        String methodName = "";
        Method method = null;

        Object valueObj = null;

        for(int i = 0; i < declaredFields.length; i ++) {
            field = declaredFields[i];
            fieldName = field.getName();
            methodName = constructMethodName(fieldName);

            method = beanCls.getMethod(methodName, null);
            valueObj = method.invoke(bean, null);

//            System.out.println("name=" + fieldName + ", value=" + valueObj);

            map.put(fieldName, valueObj);

        }

        return map;
    }

    public static String constructMethodName(String fieldName) {
        String firstLetter = fieldName.substring(0, 1);
        String suffix = fieldName.substring(1);
        String getMethodName = "get" + firstLetter.toUpperCase() + suffix;
        return getMethodName;
    }

}
