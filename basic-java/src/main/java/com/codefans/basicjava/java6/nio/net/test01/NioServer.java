package com.codefans.basicjava.java6.nio.net.test01;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.SelectorProvider;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author: caishengzhi
 * @date: 2017-10-23 15:39
 **/
public class NioServer {

    public static Map<Socket, Long> costTime = new HashMap<Socket, Long>();
    private Selector selector;
    private int port = 8182;
    private ExecutorService tp = new ThreadPoolExecutor(10, 100, 3000, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(100));
    private AtomicLong clientNum = new AtomicLong();

    public static void main(String[] args) {
        NioServer nioServer = new NioServer();
        nioServer.startServer();
    }

    public void startServer() {

        try {
            selector = SelectorProvider.provider().openSelector();
            ServerSocketChannel ssc = ServerSocketChannel.open();
            ssc.configureBlocking(false);
            InetSocketAddress isa = new InetSocketAddress(port);
            ssc.socket().bind(isa);
            // 注册感兴趣的事件，此处对accpet事件感兴趣
            SelectionKey acceptKey = ssc.register(selector, SelectionKey.OP_ACCEPT);
            for (;;) {
                selector.select();
                Set readyKeys = selector.selectedKeys();
                Iterator i = readyKeys.iterator();
                long end = 0;
                while (i.hasNext()) {
                    SelectionKey sk = (SelectionKey) i.next();
                    i.remove();
                    if (sk.isAcceptable()) {
                        doAccept(sk);
                    } else if (sk.isValid() && sk.isReadable()) {
                        if (!costTime.containsKey(((SocketChannel) sk.channel()).socket())) {
                            costTime.put(((SocketChannel) sk.channel()).socket(), System.currentTimeMillis());
                        }
                        doRead(sk);
                    } else if (sk.isValid() && sk.isWritable()) {
                        doWrite(sk);
                        end = System.currentTimeMillis();
                        long begin = costTime.remove(((SocketChannel) sk.channel()).socket());
                        System.out.println("spend:" + (end - begin) + "ms");
                    }
                }
            }



        } catch (IOException e) {
            e.printStackTrace();
        }


    }





    private void doAccept(SelectionKey sk) {

//        System.out.println("running in doAccept.....");

        // TODO Auto-generated method stub
        ServerSocketChannel server = (ServerSocketChannel) sk.channel();
        SocketChannel clientChannel;
        try {
            clientChannel = server.accept();
            clientChannel.configureBlocking(false);
            SelectionKey clientKey = clientChannel.register(selector, SelectionKey.OP_READ);
//            MultiThreadNioEchoServer.EchoClient echoClinet = new MultiThreadNioEchoServer.EchoClient();
//            clientKey.attach(echoClinet);
            InetAddress clientAddress = clientChannel.socket().getInetAddress();
            System.out.println("Accepted no.[" + clientNum.incrementAndGet() + "]connection from " + clientAddress.getHostAddress());
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    private void doRead(SelectionKey sk) {
        // TODO Auto-generated method stub
//        SocketChannel channel = (SocketChannel) sk.channel();
//        ByteBuffer bb = ByteBuffer.allocate(8192);
//        int len;
//        try {
//            len = channel.read(bb);
//            if (len < 0) {
//                disconnect(sk);
//                return;
//            }
//        } catch (Exception e) {
//            // TODO: handle exception
//            disconnect(sk);
//            return;
//        }
//        bb.flip();
//        tp.execute(new MultiThreadNioEchoServer.HandleMsg(sk, bb));

        try {

//            System.out.println("do reading.....");

            SocketChannel sc = (SocketChannel)sk.channel();
//            ByteBuffer buf = (ByteBuffer)sk.attachment();
            ByteBuffer buf = ByteBuffer.allocate(8192);

            long bytesRead = sc.read(buf);
            while(bytesRead>0){
                buf.flip();
                while(buf.hasRemaining()){
                    System.out.print((char)buf.get());
                }
                System.out.println();
                buf.clear();
                bytesRead = sc.read(buf);
            }
            if(bytesRead == -1){
                sc.close();
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void doWrite(SelectionKey sk) {
        // TODO Auto-generated method stub
//        SocketChannel channel = (SocketChannel) sk.channel();
//        MultiThreadNioEchoServer.EchoClient echoClient = (MultiThreadNioEchoServer.EchoClient) sk.attachment();
//        LinkedList<ByteBuffer> outq = echoClient.getOutputQueue();
//        ByteBuffer bb = outq.getLast();
//        try {
//            int len = channel.write(bb);
//            if (len == -1) {
//                disconnect(sk);
//                return;
//            }
//            if (bb.remaining() == 0) {
//                outq.removeLast();
//            }
//        } catch (Exception e) {
//            // TODO: handle exception
//            disconnect(sk);
//        }
//        if (outq.size() == 0) {
//            sk.interestOps(SelectionKey.OP_READ);
//        }


    }


}
