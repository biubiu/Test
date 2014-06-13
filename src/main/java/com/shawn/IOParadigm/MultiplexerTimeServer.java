package com.shawn.IOParadigm;

import com.google.common.base.Charsets;
import com.sun.management.GcInfo;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Iterator;
import java.util.Set;

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

    public static void main(String[] args){
        MultiplexerTimeServer timeServer = new MultiplexerTimeServer();
        timeServer.init(12345);
        new Thread(timeServer,"NIO-MultiplexingTimeServer-001").start();
    }
    public void init(int port){
        try{
            selector = Selector.open();
            channel = ServerSocketChannel.open();
            channel.configureBlocking(false);
            channel.socket().bind(new InetSocketAddress(port),1024);
            channel.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("The time server starts in port : " + port);

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
        while(!stop){
            try {
                selector.select(1000);
                Set<SelectionKey> selectionKeySet = selector.selectedKeys();
                Iterator<SelectionKey> keys = selectionKeySet.iterator();
                SelectionKey key = null;
                while(keys.hasNext()){
                    key = keys.next();
                    keys.remove();
                    try{
                        handleInput(key);
                    } catch (Exception e){
                        if(key!=null){
                            key.cancel();
                            if(key.channel() != null)
                                key.channel().close();
                        }
                    }
                }
            } catch (Throwable t) {
                t.printStackTrace();
            }
        }
        if(selector != null){
            try {
                selector.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    private void handleInput(SelectionKey key) throws IOException{
            if(key.isValid()){
                //deal with the incoming request message
                if(key.isAcceptable()){
                    ServerSocketChannel serverSocketChannel = (ServerSocketChannel)key.channel();
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector,SelectionKey.OP_READ);
                }

                if(key.isReadable()){
                    SocketChannel socketChannel = (SocketChannel) key.channel();
                    ByteBuffer readBuffer = ByteBuffer.allocate(1024);
                    int readBytes = socketChannel.read(readBuffer);
                    if(readBytes > 0){
                        readBuffer.flip();
                        byte[] bytes = new byte[readBuffer.remaining()];
                        readBuffer.get(bytes);
                        String body = new String(bytes, Charsets.UTF_8);
                        System.out.println("The time server receive order: " + body);
                           String currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body)? LocalDate.now().toString():"BAD ORDER";
                        doWrite(socketChannel,currentTime);
                    }
                    else if (readBytes < 0){
                        key.cancel();
                        socketChannel.close();
                    }
                    else ;
                }

            }


    }

    private void doWrite(SocketChannel channel, String response) throws IOException{
        if(response != null && response.trim().length() > 0){
            byte[] bytes = response.getBytes();
            ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);
            writeBuffer.put(bytes);
            writeBuffer.flip();
            channel.write(writeBuffer);
        }
    }


}
