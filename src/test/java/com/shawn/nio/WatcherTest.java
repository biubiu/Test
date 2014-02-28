package com.shawn.nio;

import org.junit.Test;

import com.google.common.collect.Lists;

public class WatcherTest {

    @Test
    public void watchProcess() {
        try {
            Watcher watcher = new Watcher(Lists.newArrayList("/opt/apache-tomcat-7.0.42/logs/catalina.out"));
            watcher.process();
        } catch (Exception e) {
        }

    }
}
