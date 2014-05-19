package com.shawn.IOParadigm;

import com.sun.management.GcInfo;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;

/**
 * User: Shawn cao
 * Date: 14-5-16
 * Time: PM4:21
 */
public class MultiplexerTimeServer implements  Runnable{

    private Selector selector;
    private ServerSocketChannel channel;
    private volatile boolean stop;

    private MultiplexerTimeServer(){}

    public void init(int port){
        try{
            selector = Selector.open();
            channel = ServerSocketChannel.open();
            channel.configureBlocking(false);
            channel.socket().bind(new InetSocketAddress(port),1024);
            channel.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("The time server is start in port : " + port);

        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public void stop(){
        this.stop = true;
    }

    @Override
    public void run() {

    }
}
