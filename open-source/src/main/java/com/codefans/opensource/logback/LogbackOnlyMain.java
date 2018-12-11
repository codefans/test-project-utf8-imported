package com.codefans.opensource.logback;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;
import ch.qos.logback.core.util.Loader;
import ch.qos.logback.core.util.StatusPrinter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class LogbackOnlyMain {

    public static void main(String[] args) {
        LogbackOnlyMain logbackOnlyMain = new LogbackOnlyMain();
        logbackOnlyMain.startup();
    }

    public void startup() {

        try {

            String configFilePath = "logback/logback.xml";
            this.load(configFilePath);

            Logger log = LoggerFactory.getLogger(LogbackOnlyMain.class);
            log.info("22222222");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void load(String relativeConfigPath) throws IOException, JoranException {

        LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();

        URL configurationFileUrl = Loader.getResource(relativeConfigPath, Thread.currentThread().getContextClassLoader());
        String configFilePath = configurationFileUrl.getPath();
        File externalConfigFile = new File(configFilePath);

        if(!externalConfigFile.exists()){
            throw new IOException("Logback External Config File Parameter does not reference a file that exists");
        }else{
            if(!externalConfigFile.isFile()){
                throw new IOException("Logback External Config File Parameter exists, but does not reference a file");
            }else{
                if(!externalConfigFile.canRead()){
                    throw new IOException("Logback External Config File exists and is a file, but cannot be read.");
                }else{
                    JoranConfigurator configurator = new JoranConfigurator();
                    configurator.setContext(lc);
                    lc.reset();
                    configurator.doConfigure(configFilePath);
                    StatusPrinter.printInCaseOfErrorsOrWarnings(lc);
                }
            }
        }

    }



}
