package com.codefans.reusablecode.designpattern.singleton;

/**
 * @author: ShengzhiCai
 * @date: 2018-10-26 16:48
 * 单例模式的双重检查
 * https://www.jianshu.com/p/45885e50d1c4
 */
public class StaticInnerClass {

    private static class InstanceHolder{
        public static Instance instance = new Instance();
    }

    public static Instance getInstance(){
        /**
         * 这里将导致InstanceHolder类被初始化
         */
        return InstanceHolder.instance;
    }


}
