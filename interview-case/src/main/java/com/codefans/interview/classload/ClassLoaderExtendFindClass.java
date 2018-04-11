package com.codefans.interview.classload;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author: codefans
 * @date: 2018-04-09 16:09
 */
public class ClassLoaderExtendFindClass extends ClassLoader {

    private String rootPath;

    public ClassLoaderExtendFindClass(String rootPath) {
        this.rootPath = rootPath;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
//        return super.findClass(name);
        try {
            String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";
            File file = new File(rootPath);
            if(!file.exists()) {
                System.out.println(file.getAbsolutePath());
            }
            InputStream is = new FileInputStream(file);
            if(is == null) {
                return super.findClass(name);
            }
            byte[] bytes = new byte[is.available()];
            is.read(bytes);

            return defineClass(name, bytes, 0, bytes.length);

        } catch (IOException e) {
            e.printStackTrace();
            throw new ClassNotFoundException(name);
        }


    }

//    @Override
//    public Class<?> loadClass(String name) throws ClassNotFoundException {
//        Class<?> c = null;
//
//
//
//    }



}
