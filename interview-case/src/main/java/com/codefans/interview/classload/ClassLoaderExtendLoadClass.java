package com.codefans.interview.classload;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author: codefans
 * @date: 2018-04-09 16:09
 */
public class ClassLoaderExtendLoadClass extends ClassLoader {

    private Class cls;
    public ClassLoaderExtendLoadClass(Class cls) {
        this.cls = cls;
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        Class<?> c = null;

        try {
            String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";
            InputStream is = cls.getResourceAsStream(fileName);
            if(is == null) {
                return super.loadClass(name);
            }
            byte[] bytes = new byte[is.available()];
            is.read(bytes);

            return defineClass(name, bytes, 0, bytes.length);

        } catch (IOException e) {
            e.printStackTrace();
            throw new ClassNotFoundException(name);
        }

//        return c;
    }


}
