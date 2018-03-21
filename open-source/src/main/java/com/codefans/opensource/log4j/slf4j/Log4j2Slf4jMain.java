package com.codefans.opensource.log4j.slf4j;

import com.codefans.opensource.log4j.Log4JBase;
import com.codefans.opensource.log4j.v2_10_0.Log4J2V2100Main;
import com.codefans.opensource.log4j.v2_10_0.Log4j2DebugLog;
import com.codefans.opensource.log4j.v2_10_0.Log4j2InfoLog;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * Created by caishengzhi on 2018/3/21.
 */
public class Log4j2Slf4jMain extends Log4JBase {

    private Logger log = null;
    private org.slf4j.Logger logger = null;

    public static void main(String[] args) {
        Log4j2Slf4jMain lsm = new Log4j2Slf4jMain();
        lsm.log4jSlf4JTest();
    }

    public void log4jSlf4JTest() {
        try {

//            String fileName = "log4j2_v2_10_0.properties";
            String fileName = "log4j2_v2_10_0_package_level_custom.properties";
//            String fileName = "log4j2_le_risk.xml";
//            String fileName = "log4j2_config.xml";

            String propFile = OPEN_SOURCE_RESOURCES_LOG4J + File.separator + fileName;
            System.out.println("propFile:" + propFile);

            //初始化方式1
//            System.setProperty("log4j.configurationFile", propFile);
            System.setProperty("log4j.configurationFile", "classpath:log4j/log4j2_le_risk.xml");

            //初始化方式2
//            File log4jFile = new File(propFile);
//            ConfigurationSource source = new ConfigurationSource(new FileInputStream(log4jFile), log4jFile);
//            Configurator.initialize(null, source);

            //初始化方式3-如果和spring-test集成，只能用这种方式
//            File log4jFile = new File(propFile);
//            LoggerContext loggerContext = (LoggerContext) LogManager.getContext(false);
//            loggerContext.setConfigLocation(log4jFile.toURI());


//            log = LogManager.getLogger(Log4j2Slf4jMain.class);
//
//            log.info("hello world!");
//            log.debug("hello world from debug info");

            logger = LoggerFactory.getLogger(Log4j2Slf4jMain.class);
            logger.info("dddddddddddddddddddddddddddddddddddd");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
