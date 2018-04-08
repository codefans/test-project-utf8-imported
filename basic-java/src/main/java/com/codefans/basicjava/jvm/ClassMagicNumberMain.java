package com.codefans.basicjava.jvm;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author: codefans
 * @date: 2018-04-08 15:35
 */
public class ClassMagicNumberMain {

    public static void main(String[] args) {
        ClassMagicNumberMain cmn = new ClassMagicNumberMain();
        cmn.readMagicNumber();
    }

    public void readMagicNumber() {

        String[] classFilePaths = new String[]{
            "/GitHub/test-project-utf8/basic-java/target/classes/com/codefans/basicjava/util/MyArrayList.class"
        };
        this.readMagicNumbers(classFilePaths);

    }

    public void readMagicNumbers(String... classFilePaths) {
        for(int i = 0; i < classFilePaths.length; i ++) {
            this.readMagicNumber(classFilePaths[i]);
        }
    }

    public void readMagicNumber(String classFilePath) {
        try {
            File classFile = new File(classFilePath);
            FileInputStream fis = new FileInputStream(classFile);
            int length = fis.available();
            byte[] bytes = new byte[length];

            fis.read(bytes);
            fis.close();

            parseFile(bytes);

//            int magicNumber = fis.read();
//            System.out.println("magciNumber:");
//            System.out.println(magicNumber);

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private void parseFile(byte[] data){
        //输出魔数
        System.out.print("魔数(magic):0x");

        System.out.print(Integer.toHexString(data[0]).substring(6).toUpperCase());
        System.out.print(Integer.toHexString(data[1]).substring(6).toUpperCase());
        System.out.print(Integer.toHexString(data[2]).substring(6).toUpperCase());
        System.out.println(Integer.toHexString(data[3]).substring(6).toUpperCase());

        System.out.println(data[0]);
        System.out.println(Integer.toHexString(data[0]));
        System.out.println(Integer.toHexString(data[0]).substring(6));
        System.out.println(data[1]);
        System.out.println(Integer.toHexString(data[1]));
        System.out.println(Integer.toHexString(data[1]).substring(6));
        System.out.println(data[2]);
        System.out.println(Integer.toHexString(data[2]));
        System.out.println(Integer.toHexString(data[2]).substring(6));
        System.out.println(data[3]);
        System.out.println(Integer.toHexString(data[3]));
        System.out.println(Integer.toHexString(data[3]).substring(6));

        //主版本号和次版本号码
        int minor_version = (((int)data[4]) << 8) + data[5];
        int major_version = (((int)data[6]) << 8) + data[7];
        System.out.println("版本号(version):" + major_version + "." + minor_version);


    }

}
