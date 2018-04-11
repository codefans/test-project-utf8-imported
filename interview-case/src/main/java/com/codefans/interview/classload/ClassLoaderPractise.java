package com.codefans.interview.classload;

import com.codefans.interview.algorithm.ClockwisePrintMatrix;
import com.codefans.interview.concurrent.CatchThreadException;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author: codefans
 * @date: 2018-04-09 16:02
 */
public class ClassLoaderPractise {

    public static void main(String[] args) {
        ClassLoaderPractise clp = new ClassLoaderPractise();
        clp.loadClassTest();
    }

    public void loadClassTest() {

        try {

            String clsName = "com.codefans.interview.algorithm.ClockwisePrintMatrix";
            Class cls = ClockwisePrintMatrix.class;


            ClassLoaderExtendLoadClass myClassLoader = new ClassLoaderExtendLoadClass(cls);
            System.out.println("myClassLoader----->:" + myClassLoader);
            System.out.println("myClassLoader.getParent()----->:" + myClassLoader.getParent());
            //重写loadClass，自定义类加载器;
            //Class.forName会触发初始化，而loadClass不会初始化
            Object clsObj = myClassLoader.loadClass(clsName).newInstance();

            System.out.println(clsObj.getClass());
            System.out.println(clsObj instanceof com.codefans.interview.algorithm.ClockwisePrintMatrix);

            ClassLoader clsLoader = ClockwisePrintMatrix.class.getClassLoader();
            System.out.println("clsLoader:" + clsLoader);

            //重复加载同一个类, 会报java.lang.LinkageError错误
//            Object clsObj02 = myClassLoader.loadClass(clsName).newInstance();
//            System.out.println("clsObj.equals(clsObj02):" + clsObj.equals(clsObj02));

            String filePath = "/githubForSourcetree/test-project-utf8/interview-case/target/classes/com/codefans/interview/algorithm/ClockwisePrintMatrix.class";
            ClassLoaderExtendFindClass findClassLoader = new ClassLoaderExtendFindClass(filePath);
            System.out.println("findClassLoader:" + findClassLoader);
            System.out.println("findClassLoader.getParent():" + findClassLoader.getParent());
            //建议重写findClass，而不是重写loadClass
            Class<?> clsClass = findClassLoader.findClass(clsName);
            Object clsObj03 = clsClass.newInstance();
            System.out.println("clsObj03.getClass()-->" + clsObj03.getClass());
            System.out.println("clsObj.equals(clsObj03):" + clsObj.equals(clsObj03));
            System.out.println(clsObj03 instanceof com.codefans.interview.algorithm.ClockwisePrintMatrix);

//            ClassLoader classLoader = new ClassLoader() {
//                @Override
//                public Class<?> loadClass(String name) throws ClassNotFoundException {
//                    try {
//
//                        String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";
//                        InputStream is = getClass().getResourceAsStream(name + ".class");
//                        if(is == null) {
//                            System.out.println("load class by parent class loader...");
//                            return super.loadClass(name);
//                        }
//                        byte[] bytes = new byte[is.available()];
//                        is.read(bytes);
//
//                        return defineClass(name, bytes, 0, bytes.length);
//
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                    return null;
//                }
//            };
//
//            Object obj = classLoader.loadClass(clsName).newInstance();
//            System.out.println(obj.getClass());
//            System.out.println(obj instanceof com.codefans.interview.concurrent.CatchThreadException);



        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
