package com.codefans.basicjava.classload;

import java.lang.reflect.Method;

/**
 * @author: caishengzhi
 * @date: 2017-09-26 16:13
 *  运行步骤：
 *  1.将com.codefans.basicjava.classload.ClassForInitTestBean.class文件拷贝到C:\temp\classes\com\codefans\basicjava\classload\和C:\temp\classes2\com\codefans\basicjava\classload\两个目录下
 *  2.将com.codefans.basicjava.classload.ClassForInitTestBean.java移除com.codefans.basicjava.classload包(注释: 如果没移除, 运行当前这个类, 加载到虚拟机的始终是由AppClassLoader加载的com.codefans.basicjava.classload包下的类, 而不是C:\temp\classes\或者C:\temp\classes2\目录下相应的类)
 *  3.运行本类中的main方法
 *
 **/
public class ForNameAndLoadClassTest {

    public static void main(String[] args) {
        ForNameAndLoadClassTest forNameLcTest = new ForNameAndLoadClassTest();
        forNameLcTest.test();
    }

    public void test() {
        this.forNameTest();
    }

    /**
     * 该方法输出结果如下：
         static block init...
         value+=10;finish. value=11
         forName finish...
         (cls == myCls2): true
     */
    public void forNameTest() {

        try {

            String className = "com.codefans.basicjava.classload.ClassForInitTestBean";

            String rootDir = "C:\\temp\\classes";
            FileSystemClassLoader myLoader = new FileSystemClassLoader(rootDir);
            //Class.forName会触发ClassForInitTestBean类的初始化
            Class cls = Class.forName(className, true, myLoader);
            System.out.println("forName finish...");

            String rootDir2 = "C:\\temp\\classes2";
            FileSystemClassLoader myLoader2 = new FileSystemClassLoader(rootDir2);
            //loadClass不会触发ClassForInitTestBean类的初始化
            Class myCls2 = myLoader2.loadClass(className);

            System.out.println("(cls == myCls2): " + (cls == myCls2));

        } catch (Exception e) {
            e.printStackTrace();
        }


    }


}
