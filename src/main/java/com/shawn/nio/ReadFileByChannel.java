package com.shawn.nio;

import java.io.File;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import com.google.common.primitives.Bytes;


public class ReadFileByChannel {

    private int bufferCapacity;
    private ByteBuffer byteBuffer;
    private FileChannel fileChannel;
    private File file;
    public ReadFileByChannel(int bufferCapacity, ByteBuffer byteBuffer, FileChannel fileChannel, File file) {
        super();
        this.bufferCapacity = bufferCapacity;
        this.byteBuffer = byteBuffer;
        this.fileChannel = fileChannel;
        this.file = file;
    }


    public void readFileByChange(int bufferSize){}

    public static void main(String[] args) throws Exception{


        SeekableByteChannel channel =Files.newByteChannel(Paths.get("/home/shawncao/Desktop/xx"), StandardOpenOption.READ);
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024*1024);

        byte[] bytes = new byte[0];
        while(channel.read(byteBuffer) > 0){
            byte[] temp = new byte[byteBuffer.position()];
              byteBuffer.get(temp, 0, byteBuffer.position());
              bytes = Bytes.concat(bytes,temp);
        }

        System.out.println("..." + bytes.length +" | " + 1024*1024 );
    }

}
