package com.codefans.basicjava.java6.nio.net.regular;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * @author: caishengzhi
 * @date: 2017-10-13 21:46
 **/
public class ClientSocket {

    public static void main(String[] args) throws Exception {
        ClientSocket cs = new ClientSocket();
//        cs.clientRequestTest();
        cs.multiClientRequestTest();
    }

    public void clientRequestTest() throws Exception {
        Socket client = null;
        PrintWriter writer = null;
        BufferedReader reader = null;
        try {
            client = new Socket();
            client.connect(new InetSocketAddress("localhost", 8000));
            writer = new PrintWriter(client.getOutputStream(), true);
            writer.print("this ");
            long sleepSeconds = 1 * 1000;
            Thread.sleep(sleepSeconds);
            writer.print("is ");
            Thread.sleep(sleepSeconds);
            writer.print("client ");
            Thread.sleep(sleepSeconds);
            writer.print("content");
            Thread.sleep(sleepSeconds);
            writer.print("!");

            writer.flush();
            reader = new BufferedReader(new InputStreamReader(
                    client.getInputStream()));
            System.out.println("from server: " + reader.readLine());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 省略资源关闭
            if(reader != null) {
                reader.close();
                reader = null;
            }
            if(writer != null) {
                writer.flush();
                writer.close();
                writer = null;
            }
        }
    }

    public void multiClientRequestTest() {
        int clientNum = 1000;
        for(int i = 0; i < clientNum; i ++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Socket client = null;
                    PrintWriter writer = null;
                    BufferedReader reader = null;
                    try {
                        client = new Socket();
                        client.connect(new InetSocketAddress("localhost", 8000));
                        writer = new PrintWriter(client.getOutputStream(), true);
//                        writer.println("this is client content!");

                        writer.print("this ");
                        int index = 1;
                        long sleepSeconds = index++ * 100;
                        Thread.sleep(sleepSeconds);
                        writer.print("is ");
                        Thread.sleep(sleepSeconds);
                        writer.print("client ");
                        Thread.sleep(sleepSeconds);
                        writer.print("content ");
                        Thread.sleep(sleepSeconds);
                        writer.println("! ");

                        writer.flush();
                        reader = new BufferedReader(new InputStreamReader(
                                client.getInputStream()));
                        System.out.println("from server: " + reader.readLine());
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            if(client != null) {
                                client.close();
                                client = null;
                            }
                            if(reader != null) {
                                reader.close();
                                reader = null;
                            }
                            if(writer != null) {
                                writer.flush();
                                writer.close();
                                writer = null;
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
        }
    }

}
