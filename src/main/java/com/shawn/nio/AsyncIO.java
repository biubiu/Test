package com.shawn.nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * User: Shawn cao
 * Date: 13-9-13
 * Time: PM8:54
 */
public class AsyncIO {

    static final Path file = Paths.get("/var/log/daily.out");
    public static void main(String[] args){
        //asyncFile();
          callbackFile();
    }
    public static void asyncFile(){


        try {
            AsynchronousFileChannel channel = AsynchronousFileChannel.open(file);
            ByteBuffer buffer = ByteBuffer.allocate(100_0000);
            Future<Integer> result = channel.read(buffer,0);
            while (!result.isDone()){
                System.out.println("*********");
            }
            Integer bytesRead = result.get();
            System.out.println("Byte read[" + bytesRead + "]");
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (ExecutionException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    public static void callbackFile(){
        try {
            AsynchronousFileChannel channel = AsynchronousFileChannel.open(file);
            ByteBuffer buffer = ByteBuffer.allocate(1000_0000);
            channel.read(buffer,0,buffer,new CompletionHandler<Integer, ByteBuffer>() {
                @Override
                public void completed(Integer result, ByteBuffer attachment) {
                    System.out.println("Bytes read[" + result+"]");
                }

                @Override
                public void failed(Throwable exc, ByteBuffer attachment) {
                    System.out.println(exc.getMessage());
                }
            });
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}
