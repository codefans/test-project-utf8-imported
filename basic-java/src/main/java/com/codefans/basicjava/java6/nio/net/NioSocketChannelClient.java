package com.codefans.basicjava.java6.nio.net;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @author: caishengzhi
 * @date: 2017-10-23 14:20
 **/
public class NioSocketChannelClient {

    public static void main(String[] args) {
        NioSocketChannelClient client = new NioSocketChannelClient();
//        client.request();
        client.multiThreadRequest();
    }

    public void request() {

        String hostname = "localhost";
        int port = 8001;

        ByteBuffer buffer = ByteBuffer.allocate(1024);
        SocketChannel socketChannel = null;
        try
        {
            socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(false);
            socketChannel.connect(new InetSocketAddress(hostname,port));

            if(socketChannel.finishConnect())
            {
                int i=0;
                while(true)
                {
//                    TimeUnit.SECONDS.sleep(1);
                    String info = "I'm "+i+++"-th information from client";
                    buffer.clear();
                    buffer.put(info.getBytes());
                    buffer.flip();
                    while(buffer.hasRemaining()){
                        System.out.println(buffer);
                        socketChannel.write(buffer);
                    }
                    if(i > 100000000) {
                        System.out.println("i=" + i);
                        break;
                    }
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally{
            try{
                if(socketChannel!=null){
                    socketChannel.close();
                }
            }catch(IOException e){
                e.printStackTrace();
            }
        }



    }

    public void multiThreadRequest() {
        int threadCount = 100;
        int index = 1;
        while(threadCount > 0) {
            new Thread(new ClientThread(index++)).start();
            threadCount--;
        }
    }


}

class ClientThread implements Runnable {

    int index;
    public ClientThread(int index) {
        this.index = index;
    }

    @Override
    public void run() {

        String hostname = "localhost";
        int port = 8001;

        ByteBuffer buffer = ByteBuffer.allocate(1024);
        SocketChannel socketChannel = null;
        try
        {
            socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(false);
            socketChannel.connect(new InetSocketAddress(hostname,port));

            if(socketChannel.finishConnect())
            {
                int i=0;
//                while(true) {
//                    TimeUnit.SECONDS.sleep(1);
                    String info = "I'm " + index + "-th information from client";
                    buffer.clear();
                    buffer.put(info.getBytes());
                    buffer.flip();
                    while(buffer.hasRemaining()){
                        System.out.println(buffer);
                        socketChannel.write(buffer);
                    }
//                    if(i > 100000000) {
//                        System.out.println("i=" + i);
//                        break;
//                    }
//                }



            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally{
            try{
                if(socketChannel!=null){
                    socketChannel.close();
                }
            }catch(IOException e){
                e.printStackTrace();
            }
        }

    }
}