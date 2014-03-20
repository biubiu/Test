package com.shawn.nio;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class BufferFillAndDrain {

    public static void main(String[] args) {
//        CharBuffer buffer = CharBuffer.allocate(100);
        //System.out.println(buffer.isDirect());
        ByteBuffer buffer = ByteBuffer.allocateDirect(100).order(ByteOrder.BIG_ENDIAN);
        System.out.println(buffer.isDirect() + " " + buffer.isReadOnly() + " " + buffer.order().toString());
        while(fillBuffer(buffer)){
            buffer.flip();
            drainBuffer(buffer);
            buffer.clear();
        }
    }
    private static void drainBuffer(ByteBuffer buffer){
        while(buffer.hasRemaining()){
            System.out.print((char)buffer.get());
        }
        System.out.println("");
    }

    private static boolean fillBuffer(ByteBuffer buffer){
        if(index>=strings.length)
            return false;
        String string = strings[index++];
        for (int i = 0; i < string.length(); i++) {
            buffer.put((byte)string.charAt(i));

        }
        return true;
    }

    private static int index = 0;
    private static String [] strings = {
        "A random string value",
        "The product of an infinite number of monkeys",
        "Hey hey we're the Monkees",
        "Opening act for the Monkees: Jimi Hendrix",
        "'Scuse me while I kiss this fly",
        "Help Me! Help Me!"
        };
}
