package com.codefans.basicjava.java6.nio.net.test02;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author: caishengzhi
 * @date: 2017-10-25 7:19
 **/
public class Client {

    private static String DEFAULT_HOST = "127.0.0.1";
    private static int DEFAULT_PORT = 12345;
    private static ClientHandle clientHandle;
    private static List<ClientHandle> clientHandleList = new ArrayList<ClientHandle>();
    private static AtomicLong clientIndex = new AtomicLong();

    private static ExecutorService threadPool = new ThreadPoolExecutor(8, 16, 1000, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(100000));

    public static void start(){
        start(DEFAULT_HOST,DEFAULT_PORT);
    }
    public static synchronized void start(String ip,int port){
//        if(clientHandle!=null) {
//            clientHandle.stop();
//        }
        String clientName = "Client_Thread_" + clientIndex.incrementAndGet();
        clientHandle = new ClientHandle(ip, port, clientName);
//        new Thread(clientHandle, clientName).start();
        threadPool.submit(clientHandle);

        clientHandleList.add(clientHandle);

    }
    //向服务器发送消息
    public static boolean sendMsg(String msg) throws Exception{
        if(msg.equals("q")) {
            return false;
        }
        clientHandle.sendMsg(msg);
        return true;
    }

    public static  boolean sendMsg() throws Exception {

        System.out.println("clientHandleCount:" + clientHandleList.size());

        ClientHandle handle = null;
        for(int i = 0; i < clientHandleList.size(); i ++) {
            handle = clientHandleList.get(i);
            handle.sendMsg("client_msg_" + (i+1));
        }

        return true;
    }

    public static void main(String[] args){
        try {
//            start();
//            while(Client.sendMsg(new Scanner(System.in).nextLine())) {
//                ;
//            }

            int loopCount = 500;
            while(loopCount > 0) {
                start();
                loopCount--;
            }

            sendMsg();

        } catch (Exception e) {
            e.printStackTrace();
        }


    }



}
