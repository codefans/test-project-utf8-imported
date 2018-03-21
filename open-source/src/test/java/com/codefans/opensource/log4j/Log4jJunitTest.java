package com.codefans.opensource.log4j;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.config.ConfigurationSource;
import org.apache.logging.log4j.core.config.Configurator;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;

/**
 * Created by caishengzhi on 2018/3/21.
 */
public class Log4jJunitTest {

    private Logger log = null;

    @Before
    public void before() {
        try {

//            System.setProperty("log4j.configurationFile", "classpath:log4j/log4j2_le_risk.xml");

            String propFile = System.getProperty("user.dir") + "/target/classes/log4j/log4j2_le_risk.xml";
            System.out.println("propFile:");
            System.out.println(propFile);
            File log4jFile = new File(propFile);

            ConfigurationSource source = new ConfigurationSource(new FileInputStream(log4jFile), log4jFile);
            Configurator.initialize(null, source);

//            如果和spring-test集成，只能用下面这种方式
//            LoggerContext loggerContext = (LoggerContext) LogManager.getContext(false);
//            loggerContext.setConfigLocation(log4jFile.toURI());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @Test
    public void logTest() {

        System.out.println("------------->" + System.getProperty("log4j2.debug"));

//        log = LogManager.getLogger(Log4jJunitTest.class);
        log = LoggerFactory.getLogger(Log4jJunitTest.class);

        log.info("hello world...");
        System.out.println("java.class.path=" + System.getProperty("java.class.path"));//系统的classpaht路径
        System.out.println("user.dir" + System.getProperty("user.dir"));//用户的当前路径

        log.info("logTest end...");
    }



}
