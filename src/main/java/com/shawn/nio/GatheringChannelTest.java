package com.shawn.nio;

import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.GatheringByteChannel;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Random;

import com.google.common.collect.Lists;

public class GatheringChannelTest {

    private static final String DEMOGRAPHIC = "com/shawn/nio/blahblah.txt";

    private static String[] col1 = { "Aggregate", "Enable", "Leverage", "Facilitate", "Synergize", "Repurpose", "Strategize", "Reinvent", "Harness" };
    private static String[] col2 = { "cross-platform", "best-of-breed", "frictionless", "ubiquitous", "extensible", "compelling", "mission-critical", "collaborative",
            "integrated" };
    private static String[] col3 = { "methodologies", "infomediaries", "platforms", "schemas", "mindshare", "paradigms", "functionalities", "web services",
            "infrastructures" };

    private static String newline = System.getProperty("line.separator");
    private static Random random = new Random();

    private static ByteBuffer[] utterBS(int howmany) throws Exception {
        List<ByteBuffer> list = Lists.newLinkedList();
        for (int i = 0; i < howmany; i++) {
            list.add(pickRandom(col1, " "));
            list.add(pickRandom(col2, " "));
            list.add(pickRandom(col3, newline));
        }
        ByteBuffer[] buffers = new ByteBuffer[list.size()];
        list.toArray(buffers);
        return buffers;
    }

    private static ByteBuffer pickRandom(String[] strings, String suffix) throws Exception {
        String string = strings[random.nextInt(strings.length)];
        int total = string.length() + suffix.length();
        ByteBuffer buffer = ByteBuffer.allocate(total);
        buffer.put(string.getBytes(Charset.forName("UTF-8")));
        buffer.put(suffix.getBytes(Charset.forName("UTF-8")));
        buffer.flip();
        return buffer;
    }

    public static void main(String[] args) throws Exception {
        int reps = 10;
        if (args.length > 0) {
            reps = Integer.parseInt(args[0]);
        }
        FileOutputStream fos = new FileOutputStream(DEMOGRAPHIC);
        GatheringByteChannel gatherChannel = fos.getChannel();
        // Generate some brilliant marcom, er, repurposed content
        ByteBuffer[] bs = utterBS(reps);
        // Deliver the message to the waiting market
        while (gatherChannel.write(bs) > 0) {
            // Empty body
            // Loop until write( ) returns zero
        }
        System.out.println("Mindshare paradigms synergized to " + DEMOGRAPHIC);
        fos.close();
    }
}
