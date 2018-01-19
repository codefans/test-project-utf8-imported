package com.codefans.opensource.log4j.v2_10_0;

import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.lookup.StrLookup;

/**
 * @author caishengzhi
 * @date 2018/1/19 15:19
 */
@Plugin(name = "thread", category = StrLookup.CATEGORY)
public class ThreadLookup implements StrLookup {
    @Override
    public String lookup(String key) {
        return Thread.currentThread().getName();
    }

    @Override
    public String lookup(LogEvent event, String key) {
        return event.getThreadName() == null ? Thread.currentThread().getName()
                : event.getThreadName();
    }

}