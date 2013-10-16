package com.shawn.nio;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;

/**
 * User: Shawn cao
 * Date: 13-10-12
 * Time: PM6:06
 */
public class NetworkServer {
    public static void main(String[] args) throws IOException {
        int port = 2345;
        ServerSocket socketServer = new ServerSocket(port);
        while(true){
            try {
                Socket client = socketServer.accept();
                String response = "Hello " + client.getInetAddress() + " on port " + client.getPort() +"\r\n";
                response +="This is " + client.getLocalAddress() + " on port " + client.getLocalPort() + "\r\n";
                OutputStream outputStream = client.getOutputStream();
                outputStream.write(response.getBytes(Charset.defaultCharset()));
                outputStream.flush();
                client.close();
                System.out.println("request from: " + client.getInetAddress());
            }catch (IOException e){
                System.err.print(e.getMessage());
            }
        }
    }
}
