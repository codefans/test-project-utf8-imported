package com.codefans.basicjava.java6.nio.net.test02;

/**
 * @author: caishengzhi
 * @date: 2017-10-25 7:09
 **/
public class Server {

    private static int DEFAULT_PORT = 12345;
    private static ServerHandle serverHandle;
    public static void start(){
        start(DEFAULT_PORT);
    }
    public static synchronized void start(int port){
        if(serverHandle!=null) {
            serverHandle.stop();
        }
        serverHandle = new ServerHandle(port);
        new Thread(serverHandle,"Server").start();
    }
    public static void main(String[] args){
        start();
    }

}
