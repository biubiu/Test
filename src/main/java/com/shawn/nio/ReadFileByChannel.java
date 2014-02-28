package com.shawn.nio;

import java.io.File;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;


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

}
