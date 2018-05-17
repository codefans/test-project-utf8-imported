package com.codefans.basicjava.algorithm.timeout;

import java.io.IOException;
import java.io.InputStream;
import java.net.*;
import java.util.concurrent.*;

/**
 * @Author: ShengzhiCai
 * @Date: 2018-05-17 23:00
 */

public class HttpUrlConnectionTimeout {

    public static void main(String[] args) throws IOException {


//        String urlStr = "";
//        URL url = new URL(urlStr);
//        URLConnection urlConnection = url.openConnection();
//
//        HttpURLConnection httpUrlConn = (HttpURLConnection)urlConnection;
//        int connTimeout = 1000;
//        int readTimeout = 2000;
//
//        httpUrlConn.setConnectTimeout(connTimeout);
//        httpUrlConn.setReadTimeout(readTimeout);
//
//        httpUrlConn.connect();


        Executor executor= Executors.newSingleThreadExecutor();
        FutureTask<String> future=new FutureTask<String>(new Callable<String>() {
            public String call() throws Exception {
                // TODO Auto-generated method stub
                HttpUrlConnectionTimeout m=new HttpUrlConnectionTimeout();
                return m.getValue();
            }
        });
        executor.execute(future);
        try{
            String result=future.get(1, TimeUnit.SECONDS);
            System.out.println(result);
        }catch (InterruptedException e) {
            // TODO: handle exception
            System.out.println("方法执行中断");
            // future.cancel(true);
        }catch (ExecutionException e) {
            System.out.println("Excution异常");
            // TODO: handle exception
            future.cancel(true);
        }catch (TimeoutException e) {
            // TODO: handle exception
            e.printStackTrace();
            System.out.println("方法执行时间超时");
            //future.cancel(true);
        }
        System.out.println("爱上大声地");



//
//        Socket socket = new Socket("127.0.0.1", 8080);
//        socket.setSoTimeout(1000);
//        InputStream is = socket.getInputStream();



    }

    public String getValue(){
        try{
            Thread.sleep(2000);
        }catch (Exception e) {

            e.printStackTrace();// TODO: handle exception
        }
        return "阿斯顿撒旦阿斯顿 sad";
    }



}
