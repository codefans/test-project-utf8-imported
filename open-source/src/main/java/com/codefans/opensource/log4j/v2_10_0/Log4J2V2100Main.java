package com.codefans.opensource.log4j.v2_10_0;

import com.codefans.opensource.log4j.Log4JBase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.config.ConfigurationSource;
import org.apache.logging.log4j.core.config.Configurator;

import java.io.File;
import java.io.FileInputStream;

/**
 * @author caishengzhi
 * @date 2018/1/18 17:46
 */
public class Log4J2V2100Main extends Log4JBase {

//    private Logger log = LogManager.getLogger(Log4J2V2100Main.class);
    private Logger log = null;

    public static void main(String[] args) {
        Log4J2V2100Main v129Main = new Log4J2V2100Main();
        v129Main.init();
    }

    public void init() {


        try {

//            String fileName = "log4j2_v2_10_0.properties";
            String fileName = "log4j2_v2_10_0_package_level_custom.properties";
//            String fileName = "log4j2_le_risk.xml";
//            String fileName = "log4j2_config.xml";

            String propFile = OPEN_SOURCE_RESOURCES_LOG4J + File.separator + fileName;
            System.out.println("propFile:" + propFile);

            //初始化方式1
            System.setProperty("log4j.configurationFile", propFile);

            //初始化方式2
//            File log4jFile = new File(propFile);
//            ConfigurationSource source = new ConfigurationSource(new FileInputStream(log4jFile), log4jFile);
//            Configurator.initialize(null, source);

            //初始化方式3
//            File log4jFile = new File(propFile);
//            LoggerContext loggerContext = (LoggerContext) LogManager.getContext(false);
//            loggerContext.setConfigLocation(log4jFile.toURI());


            log = LogManager.getLogger(Log4J2V2100Main.class);

            log.info("hello world!");
            log.debug("hello world from debug info");

            new Log4j2DebugLog();
            new Log4j2InfoLog();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }




}

