package com.codefans.reusablecode.designpattern.singleton;

/**
 * @author: ShengzhiCai
 * @date: 2018-10-26 16:23
 */
public class DoubleCheck {

    /**
     * 静态实例变量加上volatile
     * 用volatile关键字的原因是:为了防止指令重排序
     *
     * instance = new DoubleCheck();
     * 这一句主要分三个步骤：
     *      memory=allocate();     //1:分配对象的内存空间
            ctorInstance(memory);  //2:初始化对象
            instance = memory;     //3:设置instance指向刚分配的内存地址

        如果2,3执行顺序调换, 即：
            memory=allocate();     //1:分配对象的内存空间
            instance = memory;     //3:设置instance指向刚分配的内存地址
            ctorInstance(memory);  //2:初始化对象
        那么当3执行完后, 如果另一个线程正好进行第一次null判断, 这时instance不为null, 直接返回instance了,
        但是此时instance还没有初始化, 会出问题的, 所以需要加volatile关键字. 

      */
    private static volatile DoubleCheck instance;

    /**
     * 私有化构造函数
      */
    private DoubleCheck() {
    }

    /**
     * 双重检查锁
     *
     *
      */
    public static DoubleCheck getInstance() {
        /**
         * 第一个null判断, 是为了提高效率,
         * 这样就不会每次调用这个方法都加锁, 而只会在第一次调用的时候会进入加锁代码块
         */
        if (instance == null) {
            synchronized(DoubleCheck.class){
                /**
                 * 第二个null判断, 是为了防止创建多个对象
                 */
                if(instance == null){
                    instance = new DoubleCheck();
                }
            }
        }
        return instance;
    }

}
