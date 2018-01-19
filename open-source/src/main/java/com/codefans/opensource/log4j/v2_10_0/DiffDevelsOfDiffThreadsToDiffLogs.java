package com.codefans.opensource.log4j.v2_10_0;

import com.codefans.opensource.log4j.Log4JBase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;

/**
 * @author caishengzhi
 * @date 2018/1/19 15:20
 * Log4j2实现不同线程不同级别日志输出到不同的文件中
https://my.oschina.net/u/2300159/blog/887687

 */
public class DiffDevelsOfDiffThreadsToDiffLogs extends Log4JBase {

    private Logger log = null;

    public static void main(String[] args) {
        DiffDevelsOfDiffThreadsToDiffLogs diffMain = new DiffDevelsOfDiffThreadsToDiffLogs();
        diffMain.runTest();
    }

    public void runTest() {

        String fileName = "log4j2_diffLevelsOfDiffThreadsToDiffLogs.xml";

        String propFile = OPEN_SOURCE_RESOURCES_LOG4J + File.separator + fileName;
        System.out.println("propFile:" + propFile);

        //初始化方式1
        System.setProperty("log4j.configurationFile", propFile);

        log = LogManager.getLogger(DiffDevelsOfDiffThreadsToDiffLogs.class);

        log.info("hello world!");
        log.debug("hello world from debug info");

        new Thread(new Runnable() {
            @Override
            public void run() {
                log.info("info");
                log.debug("debug");
                log.error("error");
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                log.info("info");
                log.debug("debug");
                log.error("error");
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                log.info("info");
                log.debug("debug");
                log.error("error");
            }
        }).start();


    }

}
