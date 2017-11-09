package com.codefans.basicjava.java6.nio.buffer;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author: caishengzhi
 * @date: 2017-10-19 17:46
 **/
public class RegularIoAndNioCompare {

//    private final String currentDir = "basic-java/src/main/java6/com/codefans/basicjava/java/nio/buffer/";
    private final String currentDir = "C:/temp/";
//    private final String inFileName = "test.txt";
    private final String inFileName = "Downloads.rar";
    private File inFile = null;

    private final String regularIoOutFileName = inFileName + ".regularIoOutFile.out";
    private final String nioOutFileName = inFileName + ".nioOutFile.out";

    public static void main(String[] args) {
        RegularIoAndNioCompare ioAndNioCompare = new RegularIoAndNioCompare();
        ioAndNioCompare.compare();
    }

    public void compare() {
        //传统IO复制文件[Downloads.rar], 文件大小为:[1312266KB], 总耗时为[38s]
//        this.regularIoCopyFile();
        //nio复制文件[Downloads.rar], 文件大小为:[1312266KB], 总耗时为[27s]
        this.nioCopyFile();
    }

    /**
     * 传统IO复制文件
     */
    public void regularIoCopyFile() {
        BufferedInputStream in = null;
        BufferedOutputStream bos = null;
        try{

            long startTime = System.currentTimeMillis();
            inFile = new File(currentDir + inFileName);
            in = new BufferedInputStream(new FileInputStream(inFile));
            bos = new BufferedOutputStream((new FileOutputStream(currentDir + regularIoOutFileName)));

            byte [] buf = new byte[1024];
            int bytesRead = 0;
            while((bytesRead = in.read(buf)) != -1)
            {
                bos.write(buf, 0, bytesRead);
            }


            long endTime = System.currentTimeMillis();
            System.out.println("传统IO复制文件[" + inFileName + "], 文件大小为:[" + (inFile.length()/1024) + "KB], 总耗时为[" + (endTime - startTime)/1000 + "s]");

        }catch (IOException e)
        {
            e.printStackTrace();
        }finally{
            try{
                if(in != null){
                    in.close();
                }
                if(bos != null){
                    bos.flush();
                    bos.close();
                    bos = null;
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    /**
     * NIO复制文件
     */
    public void nioCopyFile() {
        RandomAccessFile aFile = null;
        FileChannel readChannel = null;
        FileChannel writeChannel = null;

        try{
//            aFile = new RandomAccessFile(currentDir + inFileName,"rw");
//            FileChannel fileChannel = aFile.getChannel();
//            ByteBuffer buf = ByteBuffer.allocate(1024);
//
//            int bytesRead = fileChannel.read(buf);
//            System.out.println("第一次读取内容的长度为:[" + bytesRead + "]bytes");
//
//            while(bytesRead != -1)
//            {
//                buf.flip();
//                while(buf.hasRemaining())
//                {
//                    System.out.print((char)buf.get());
//                }
//
//                buf.compact();
//                bytesRead = fileChannel.read(buf);
//            }

            long startTime = System.currentTimeMillis();
            inFile = new File(currentDir + inFileName);
            FileInputStream fis = new FileInputStream(inFile);

            FileOutputStream fos = new FileOutputStream(currentDir + nioOutFileName);
            readChannel = fis.getChannel(); // 读文件通道
            writeChannel = fos.getChannel(); // 写文件通道
            ByteBuffer buffer = ByteBuffer.allocate(1024); // 读入数据缓存
            while (true) {
                buffer.clear();
                int len = readChannel.read(buffer); // 读入数据
                if (len == -1) {
                    break; // 读取完毕
                }
                buffer.flip();
                writeChannel.write(buffer); // 写入文件
            }

            long endTime = System.currentTimeMillis();
            System.out.println("nio复制文件[" + inFileName + "], 文件大小为:[" + inFile.length()/1024 + "KB], 总耗时为[" + (endTime - startTime)/1000 + "s]");

        }catch (IOException e){
            e.printStackTrace();
        }finally{
            try{
                if(aFile != null){
                    aFile.close();
                }
                if(readChannel != null){
                    readChannel.close();
                    readChannel = null;
                }
                if(writeChannel != null){
                    writeChannel.close();
                    writeChannel = null;
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

}
