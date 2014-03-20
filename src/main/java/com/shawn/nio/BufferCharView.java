package com.shawn.nio;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.CharBuffer;

public class BufferCharView {
    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(13).order(ByteOrder.BIG_ENDIAN);
        CharBuffer viewBuffer = buffer.asCharBuffer();

        buffer.putChar('H');
        buffer.putChar('E');
        buffer.putChar('L');
        buffer.putChar('L');
        buffer.putChar('O');
        buffer.putChar('H');
        /*buffer.put(1, (byte) 'H');
        buffer.put(2, (byte) 0);
        buffer.put(3, (byte) 'E');
        buffer.put(4, (byte) 0);
        buffer.put(5, (byte) 'L');
        buffer.put(6, (byte) 0);
        buffer.put(7, (byte) 'L');
        buffer.put(8, (byte) 0);
        buffer.put(9, (byte) 'O');
        buffer.put(10, (byte) 0);
        buffer.put(11, (byte) 'M');*/
        println(buffer);
        println(viewBuffer);

    }

    public static void println(Buffer buffer){
        System.out.println ("pos=" + buffer.position()+ ", limit=" + buffer.limit( )
                + ", capacity=" + buffer.capacity( )
                + ": '" + buffer.toString( ) + "'");
    }
}

