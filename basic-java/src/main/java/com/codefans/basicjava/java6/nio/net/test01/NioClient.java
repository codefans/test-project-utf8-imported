package com.codefans.basicjava.java6.nio.net.test01;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author: caishengzhi
 * @date: 2017-10-23 15:39
 **/
public class NioClient implements Runnable {

    private AtomicLong clientNum = new AtomicLong();
    String hostname = "localhost";
    int port = 8182;
    int index = 0;
    private static int currentIndex = 1;

    public NioClient() {

    }

    public NioClient(int index) {
        this.index = index;
    }

    public static void main(String[] args) {
        NioClient nioClient = new NioClient();
        nioClient.multiThreadRequest();
//        nioClient.request();
    }

    public void request() {
        new Thread(new NioClient(currentIndex++)).start();
    }

    public void multiThreadRequest() {
        int threadNum = 1000;
        while(threadNum > 0) {
            request();
            threadNum--;
        }

//        try {
//            TimeUnit.SECONDS.sleep(5 * 1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

    }

    @Override
    public void run() {

        ByteBuffer buffer = ByteBuffer.allocate(1024);
        SocketChannel socketChannel = null;
        try
        {
            socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(false);
            socketChannel.connect(new InetSocketAddress(hostname,port));

            if(socketChannel.finishConnect()) {
//                int i=0;
//                while(true) {

//                    TimeUnit.SECONDS.sleep(1);

                int localPort = socketChannel.socket().getLocalPort();

                    String info = "I'm " + index +"-th information from client, localPort=[" + localPort + "]";
                System.out.println(info);
                    buffer.clear();
                    buffer.put(info.getBytes());
                    buffer.flip();
                    while(buffer.hasRemaining()){
//                        System.out.println(buffer);
                        socketChannel.write(buffer);
                    }
//                    if(i > 100000000) {
//                        System.out.println("i=" + i);
//                        break;
//                    }
//                }
            } else {
                System.out.println("socketChannel.isConnected(): " + socketChannel.isConnected());
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(socketChannel!=null) {
                    socketChannel.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }


}
