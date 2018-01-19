package com.codefans.opensource.log4j.v1_2_9;

import com.codefans.opensource.log4j.Log4JBase;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.io.File;

/**
 * @author caishengzhi
 * @date 2017/12/25 15:46
 */
public class V129Main extends Log4JBase {

    private Logger log = LogManager.getLogger(V129Main.class);

    public static void main(String[] args) {
        V129Main v129Main = new V129Main();
        v129Main.init();
    }

    public void init() {

        String propFile = OPEN_SOURCE_RESOURCES_ROOT + File.separator + "log4j/log4j_v1_2_9.properties";
        System.out.println("propFile:" + propFile);

        PropertyConfigurator.configure(propFile);

        log.info("hello world!");
    }




}
