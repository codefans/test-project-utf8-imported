package com.codefans.basicjava.java6.nio.net.regular;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;

/**
 * @author: caishengzhi
 * @date: 2017-10-13 18:26
 **/
public class MultiThreadSocketServer {

    public static void main(String[] args) throws Exception {
        ServerSocket echoServer = null;
        Socket clientSocket = null;
        try {
            echoServer = new ServerSocket(8000);
        } catch (IOException e) {
            System.out.println(e);
        }


//        ExecutorService executorService = Executors.newFixedThreadPool(10);

        int corePoolSize = 10;
        int maximumPoolSize = 100;
        long keepAliveTime = 5000L;
        TimeUnit unit = TimeUnit.MILLISECONDS;
        BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<Runnable>(100);
        ThreadFactory threadFactory = new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r, "t_thread_pool_");
            }
        };

        ExecutorService executorService = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory);

        int clientNum = 1;
        while (true) {
            try {
                clientSocket = echoServer.accept();
                System.out.println(clientSocket.getRemoteSocketAddress()
                        + " connect! client:[" + (clientNum++) + "]");

//                executorService.execute(new HandleMsg(clientSocket));

                new Thread(new HandleMsg((clientSocket))).start();

            } catch (IOException e) {
                System.out.println(e);
            }
        }




    }

}
