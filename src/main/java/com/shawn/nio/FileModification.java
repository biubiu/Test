package com.shawn.nio;

import com.google.common.base.Joiner;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * User: Shawn cao
 * Date: 13-9-13
 * Time: PM7:23
 */
public class FileModification {
    final private static Path path= Paths.get("/Users/caocao024/log.txt");

    public static void main(String[] args){
       // readFile();
       // writeFile();
       //simpleRead();
       watchService();
       //seekByChannel();
    }

    public static void writeFile(){
        try(BufferedWriter writer = Files.newBufferedWriter(path,StandardCharsets.UTF_8, StandardOpenOption.APPEND)){
                writer.write("hello2");
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    private static void readFile(){
        try(BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)){
            String line;
            while((line=reader.readLine())!=null){
                System.out.format("----| %s \n",line);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private  static void simpleRead(){
        try {
            List<String> lines = Files.readAllLines(path,StandardCharsets.UTF_8);
            System.out.println(Joiner.on(";").join(lines));

        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    public static void  watchService(){
        try {
            WatchService watcher = FileSystems.getDefault().newWatchService();
            WatchKey watchKey= Paths.get("/Users/caocao024/Desktop").register(watcher,StandardWatchEventKinds.ENTRY_MODIFY);
            while(true) {
                //watchKey = watcher.take();
                for(WatchEvent<?> event:watcher.poll(10, TimeUnit.MILLISECONDS).pollEvents()){
                    if(event.kind() == StandardWatchEventKinds.ENTRY_MODIFY){
                        System.out.println("====" + event.context() + " | " + event.count() );
                    }
                }
                watchKey.reset();
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    public static void seekByChannel(){
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        try {
            FileChannel channel = FileChannel.open(Paths.get("/var/log/daily.out"),StandardOpenOption.READ);
            channel.read(buffer,channel.size()-1024);
            System.out.println("---"+(char)buffer.get());
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}
