package com.codefans.basicjava.java.nio.buffer;

import org.junit.Test;

import java.io.*;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author: caishengzhi
 * @date: 2017-10-26 10:59
 **/
public class NioBufferTest {

    private final String rootDir = "src/test/java/com/codefans/basicjava/java/nio/buffer/";

    @Test
    public void nioRead() {

        FileInputStream fis = null;
        FileChannel fc = null;
        ByteBuffer byteBuffer = null;
        try {

            String filePath = rootDir + "in.txt";
            fis = new FileInputStream(filePath);
            fc = fis.getChannel();

            byteBuffer = ByteBuffer.allocate(1024);
            int readBytes = 0;
            byte[] bytes = null;

            this.print("初始化", byteBuffer);

            while((readBytes = fc.read(byteBuffer)) != -1) {

                this.print("调用read()之后", byteBuffer);

                byteBuffer.flip();

                this.print("调用flip()之后", byteBuffer);

                int remaining = byteBuffer.remaining();
                bytes = new byte[remaining];
                byteBuffer.get(bytes);

                this.print("调用get()之后", byteBuffer);

                System.out.println(new String(bytes, 0, bytes.length));

                byteBuffer.rewind();

                this.print("调用rewind()之后", byteBuffer);

                remaining = byteBuffer.remaining();
                bytes = new byte[remaining];
                byteBuffer.get(bytes);

                this.print("再次调用get()之后", byteBuffer);

                byteBuffer.rewind();

                this.print("再次调用rewind()之后", byteBuffer);

                System.out.println(new String(bytes, 0, bytes.length));

//                byteBuffer.compact();

                byteBuffer.clear();



            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(fis);
        }


    }

    @Test
    public void nioWrite() {

        FileOutputStream fos = null;
        FileChannel fcOut = null;
        ByteBuffer byteBuffer = null;
        try {

            String outFilePath = rootDir + "out.txt";
            fos = new FileOutputStream(outFilePath);
            fcOut = fos.getChannel();

            byteBuffer = ByteBuffer.allocate(1024);

            this.print("初始化", byteBuffer);

            byteBuffer.put("hello world".getBytes());

            this.print("调用put()之后", byteBuffer);

            byteBuffer.flip();

            this.print("调用flip()之后", byteBuffer);

            fcOut.write(byteBuffer);

            this.print("调用write()之后", byteBuffer);

            byteBuffer.clear();

            this.print("调用clear()之后", byteBuffer);


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(fos);
            close(fcOut);
        }


    }

    @Test
    public void nioCopy() {

        FileInputStream fis = null;
        FileOutputStream fos = null;
        FileChannel fc = null;
        FileChannel fcOut = null;
        ByteBuffer byteBuffer = null;
        try {

            String filePath = rootDir + "in.txt";
            fis = new FileInputStream(filePath);
            fc = fis.getChannel();

            String outFilePath = rootDir + "out.txt";
            fos = new FileOutputStream(outFilePath);
            fcOut = fos.getChannel();

            byteBuffer = ByteBuffer.allocate(1024);
            int readBytes = 0;
            byte[] bytes = null;
            int position = 0;
            int capacity = 0;
            int limit = 0;
            this.print("初始化", byteBuffer);

            while((readBytes = fc.read(byteBuffer)) != -1) {

                this.print("调用read()之后", byteBuffer);

                byteBuffer.flip();

                this.print("调用flip()之后", byteBuffer);

                fcOut.write(byteBuffer);

                this.print("调用write()之后", byteBuffer);

                byteBuffer.clear();

                this.print("调用clear()之后", byteBuffer);

            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(fis, fos);
            close(fc);
            close(fcOut);
        }


    }

    public void print(String msg, Buffer buffer) {
        System.out.println(msg + " : ");
        System.out.print("position: " + buffer.position() + ", ");
        System.out.print("capacity: " + buffer.capacity() + ", ");
        System.out.print("remaining: " + buffer.remaining() + ", ");
        System.out.println("limit: " + buffer.limit());
        System.out.println();
    }

    public void close(InputStream is) {
        try {
            if(is != null) {
                is.close();
                is = null;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void close(OutputStream os) {
        try {
            if(os != null) {
                os.close();
                os = null;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void close(InputStream is, OutputStream os) {
        this.close(is);
        this.close(os);
    }

    public void close(FileChannel fileChannel) {
        try {
            if(fileChannel != null) {
                fileChannel.close();
                fileChannel = null;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
