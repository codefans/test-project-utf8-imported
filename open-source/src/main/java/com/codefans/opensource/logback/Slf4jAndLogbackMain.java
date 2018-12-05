package com.codefans.opensource.logback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 需要将open-source下的pom.xml文件中的log4j-slf4j-impl注释掉在运行这个main方法
 * <dependency>
 *    <groupId>org.apache.logging.log4j</groupId>
 *    <artifactId>log4j-slf4j-impl</artifactId>
 * </dependency>
 *
 * 否则回报如下错误：
 * SLF4J: Found binding in [jar:file:/maven_repository/org/apache/logging/log4j/log4j-slf4j-impl/2.10.0/log4j-slf4j-impl-2.10.0.jar!/org/slf4j/impl/StaticLoggerBinder.class]
 * SLF4J: Found binding in [jar:file:/maven_repository/ch/qos/logback/logback-classic/1.2.3/logback-classic-1.2.3.jar!/org/slf4j/impl/StaticLoggerBinder.class]
 * SLF4J: See http://www.slf4j.org/codes.html#multiple_bindings for an explanation.
 * SLF4J: Actual binding is of type [org.apache.logging.slf4j.Log4jLoggerFactory]
 * java.lang.ClassCastException: org.apache.logging.slf4j.Log4jLoggerFactory cannot be cast to ch.qos.logback.classic.LoggerContext
 * 	at com.codefans.opensource.logback.LogbackInit.initLogback(LogbackInit.java:22)
 * 	at com.codefans.opensource.logback.Slf4jAndLogbackMain.startup(Slf4jAndLogbackMain.java:25)
 * 	at com.codefans.opensource.logback.Slf4jAndLogbackMain.main(Slf4jAndLogbackMain.java:17)
 *
 * 意思是有两个：StaticLoggerBinder，冲突了
 *
 */
public class Slf4jAndLogbackMain {

    public static void main(String[] args) {
        Slf4jAndLogbackMain slf4jAndLogbackMain = new Slf4jAndLogbackMain();
        slf4jAndLogbackMain.startup();
    }

    public void startup() {

        Logger log = null;
        try {
            String configFilePath = System.getProperty("user.dir") + "/open-source/src/main/resources/logback/logback.xml";
            LogbackInit.initLogback(configFilePath);

            log = LoggerFactory.getLogger(Slf4jAndLogbackMain.class);
            log.info("slf4j and logback init success!");

            String param = "哈哈";
            log.info("测试info方法, param={}", param);

            String str = null;
            System.out.println(str.toString());

        } catch (Exception e) {
            e.printStackTrace();
            log.error("未知异常", e);
        }


    }

}
