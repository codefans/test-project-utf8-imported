package com.codefans.basicjava.java7.aio;

/**
 * @author: caishengzhi
 * @date: 2017-10-25 23:26
 **/
public class AioServer {

    private static int DEFAULT_PORT = 12345;
    private static AsyncServerHandler serverHandle;
    public volatile static long clientCount = 0;
    public static void start(){
        start(DEFAULT_PORT);
    }
    public static synchronized void start(int port){
        if(serverHandle!=null) {
            return;
        }
        serverHandle = new AsyncServerHandler(port);
        new Thread(serverHandle,"Server").start();
    }
    public static void main(String[] args){
        AioServer.start();
    }

}
