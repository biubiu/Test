package com.shawn.nio;


import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.google.common.collect.Lists;
import com.google.common.util.concurrent.ThreadFactoryBuilder;

public class Watcher {


    public static void main(String[] args) throws Exception{
        Watcher watcher = new Watcher(Lists.newArrayList("/opt/apache-tomcat-7.0.42/logs/catalina.out","",""));
        watcher.process();
    }

    private ScheduledExecutorService scheduledExecutorService;
    private final List<String> watchFilePaths;
    private int delay = 2000;
    private ByteBuffer byteBuffer ;
    private int bufferCapacity;
    //private final WatchService watchService ;

    public Watcher(List<String> filePath) throws Exception{
      //  watchService= fileSystem.newWatchService();
        watchFilePaths = filePath;
        byteBuffer = ByteBuffer.allocate(bufferCapacity);
        scheduledExecutorService = Executors.newScheduledThreadPool(watchFilePaths.size(),new ThreadFactoryBuilder().setNameFormat("file-watcher-sechdule").build());

    }

    public void process(){
        for(String path : watchFilePaths){
            //scheduledExecutorService.schedule(new FileChangeRunnable(new File(path),byteBuffer), delay, TimeUnit.MILLISECONDS);
                scheduledExecutorService.scheduleWithFixedDelay(new FileChangeRunnable(new File(path),byteBuffer), 0, delay, TimeUnit.MILLISECONDS);
        }
        try {
            Thread.sleep(10000);
            System.out.println("---" + Thread.activeCount());
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    class FileChangeRunnable implements Runnable{
        private File file;
        private long lastModifiedSize;
        private long lastModifiedTime;
        private ByteBuffer byteBuffer;

        public FileChangeRunnable(File file,ByteBuffer byteBuffer){
            this.file = file;
            lastModifiedTime = file.lastModified();
            lastModifiedSize = file.length();
            this.byteBuffer = byteBuffer;
        }

        public void run() {
            System.out.println( " oringin: " + lastModifiedSize + Thread.currentThread().getName());
            if(file.lastModified() > lastModifiedTime && file.length() > lastModifiedSize){
               System.out.println(file.length() + " | oringin: " + lastModifiedSize);
                try {
                    int readLength = (int)(file.length() - lastModifiedSize);
                    byteBuffer = ByteBuffer.allocate(readLength);
                    SeekableByteChannel seekableByteChannel = Files.newByteChannel(Paths.get(file.getPath()), StandardOpenOption.READ);
                    String encoding = System.getProperty("file.encoding");
                    byteBuffer.clear();
                    seekableByteChannel.position(lastModifiedSize);

                    while (seekableByteChannel.read(byteBuffer) > 0) {
                        byteBuffer.flip();
                        System.out.println("---: " + Charset.forName(encoding).decode(byteBuffer));
                        byteBuffer.clear();
                    }
                    lastModifiedTime = file.lastModified();
                    lastModifiedSize = file.length();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}