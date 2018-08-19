package com.codefans.reusablecode.common;

import java.io.File;

/**
 * @author: ShengzhiCai
 * @date: 2018-08-20 00:58
 */
public class CommonUtils {

    public static final String PROJECT_ROOT = System.getProperty("user.dir");

    public static String getModuleRoot(String moduleName) {
        return PROJECT_ROOT + File.separator + moduleName;
    }

    public static String getModuleSrcRoot(String moduleName) {
        return getModuleRoot(moduleName) + File.separator + "src";
    }

    public static String getModuleMainRoot(String moduleName) {
        return getModuleSrcRoot(moduleName) + File.separator + "main";
    }

    public static String getModuleTestRoot(String moduleName) {
        return getModuleSrcRoot(moduleName) + File.separator + "test";
    }

    public static String getModuleMainJavaRoot(String moduleName) {
        return getModuleMainRoot(moduleName) + File.separator + "java";
    }

    public static String getModuleTestJavaRoot(String moduleName) {
        return getModuleMainRoot(moduleName) + File.separator + "test";
    }

}
