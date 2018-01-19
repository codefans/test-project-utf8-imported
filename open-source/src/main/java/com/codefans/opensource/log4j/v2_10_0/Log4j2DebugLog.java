package com.codefans.opensource.log4j.v2_10_0;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author caishengzhi
 * @date 2018/1/19 10:55
 */
public class Log4j2DebugLog {

    private Logger log = LogManager.getLogger(Log4j2DebugLog.class);

    public Log4j2DebugLog() {
        log.debug("Log4j2DebugLog debug logging...");
        log.info("Log4j2DebugLog info logging...");
    }


}
