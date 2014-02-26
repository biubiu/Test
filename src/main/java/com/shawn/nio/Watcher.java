package com.shawn.nio;


import java.io.File;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.google.common.collect.Lists;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.google.common.util.concurrent.ThreadFactoryBuilder;

public class Watcher {

    private  final EventBus eventBus;
    private ScheduledExecutorService scheduledExecutorService;
    private final List<String> watchFilePaths;
//    private final FileSystem fileSystem = FileSystems.getDefault();
    private int delay;
    //private final WatchService watchService ;
    public Watcher() throws Exception{
        eventBus = new EventBus();
      //  watchService= fileSystem.newWatchService();
        watchFilePaths = Lists.newArrayList();
        scheduledExecutorService = Executors.newScheduledThreadPool(watchFilePaths.size(),new ThreadFactoryBuilder().setNameFormat("file-watcher-sechdule").build());

    }

    public void run(){

        for(String path : watchFilePaths){
            scheduledExecutorService.schedule(new FileChangeRunnable(new File(path),eventBus), delay, TimeUnit.MILLISECONDS);
        }

    }

    class FileChangeObserver{

        @Subscribe
        public void onMessage(){

        }
    }

    class FileChangeRunnable implements Runnable{
        private final File file;
        private long lastModifiedSize;
        private long lastModifiedTime;
        private EventBus eventBus;
        public FileChangeRunnable(File file,EventBus eventBus){
            this.file = file;
            lastModifiedTime = file.lastModified();
            lastModifiedSize = file.length();
            this.eventBus = eventBus;
            eventBus.register(new FileChangeObserver());

        }

        public void run() {
            if(file.lastModified() > lastModifiedTime && file.length() > lastModifiedSize){

            }
        }

    }
}