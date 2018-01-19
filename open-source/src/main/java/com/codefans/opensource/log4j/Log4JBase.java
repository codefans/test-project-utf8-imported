package com.codefans.opensource.log4j;

import java.io.File;

/**
 * @author caishengzhi
 * @date 2018/1/18 17:30
 */
public class Log4JBase {

    //D:\github\test-project-utf8
    public static String PROJECT_ROOT = System.getProperty("user.dir");

    public static String OPEN_SOURCE_ROOT  = PROJECT_ROOT + File.separator + "open-source";

    public static String OPEN_SOURCE_RESOURCES_ROOT  = OPEN_SOURCE_ROOT + File.separator + "src/main/resources";

    public static String OPEN_SOURCE_RESOURCES_LOG4J  = OPEN_SOURCE_RESOURCES_ROOT + File.separator + "log4j";


}
