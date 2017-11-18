package com.codefans.basicjava.classload;

/**
 * @author: caishengzhi
 * @date: 2017-09-27 17:03
 **/
public class ClassLoaderTest {

    public static void main(String[] args) {
        ClassLoaderTest clt = new ClassLoaderTest();
        clt.test();
    }

    public void test() {
        this.classLoaderTypeTest();
    }

    public void classLoaderTypeTest() {
        try {

            String className = "com.codefans.basicjava.classload.ClassForInitTestBean";

            ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
            Class cls = systemClassLoader.loadClass(className);
//          以下语句输出：systemClassLoader:[sun.misc.Launcher$AppClassLoader@558fe7c3], cls.classLoader:[sun.misc.Launcher$AppClassLoader@558fe7c3], parent:[sun.misc.Launcher$ExtClassLoader@5ab8df17], root:[null]
            System.out.println("systemClassLoader:[" + systemClassLoader + "], cls.classLoader:[" + cls.getClassLoader() + "], parent:[" + cls.getClassLoader().getParent() + "], root:[" + cls.getClassLoader().getParent().getParent() + "]");

            ClassLoader appClassLoader = ForNameAndLoadClassTest.class.getClassLoader();
            Class appClass = appClassLoader.loadClass(className);
//          以下语句输出：appClassLoader:[sun.misc.Launcher$AppClassLoader@558fe7c3], appClass.classLoader:[sun.misc.Launcher$AppClassLoader@558fe7c3], parent:[sun.misc.Launcher$ExtClassLoader@5ab8df17], root:[null]
            System.out.println("appClassLoader:[" + appClassLoader + "], appClass.classLoader:[" + appClass.getClassLoader() + "], parent:[" + appClass.getClassLoader().getParent() + "], root:[" + cls.getClassLoader().getParent().getParent() + "]");


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }

}
