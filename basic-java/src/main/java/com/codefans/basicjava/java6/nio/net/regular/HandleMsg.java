package com.codefans.basicjava.java6.nio.net.regular;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author: caishengzhi
 * @date: 2017-10-13 18:27
 **/
public class HandleMsg implements Runnable {

    private Socket clientSocket;
    private BufferedReader br;
    private PrintWriter pw;

    private AtomicLong threadCount = new AtomicLong();

    public HandleMsg(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    //省略部分信息
    @Override
    public void run(){
        try {
            br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            pw = new PrintWriter(clientSocket.getOutputStream(), true);
            // 从InputStream当中读取客户端所发送的数据
            String inputLine = null;
            long b = System.currentTimeMillis();
            long threadIndex = threadCount.incrementAndGet();

            while ((inputLine = br.readLine()) != null)
            {
                System.out.println("content from client:[" + inputLine + "], thread No.[" + threadIndex + "]");
                pw.println("this is server content!, thread num:[" + threadIndex + "]");
            }
            long e = System.currentTimeMillis();
            System.out.println ("spend:"+(e - b)+" ms ");
        } catch (IOException e) {
            e.printStackTrace();
        }finally
        {
            try {

                if(clientSocket != null) {
                    clientSocket.close();
                    clientSocket = null;
                }

                if(br != null) {
                    br.close();
                    br = null;
                }
                if(pw != null) {
                    pw.flush();
                    pw.close();
                    pw = null;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
