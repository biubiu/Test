package com.shawn.IOParadigm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDate;

/**
 * User: Shawn cao
 * Date: 14-5-16
 * Time: PM4:25
 */
public class TimeServer {

    public static void main(String[] args){
        TimeServer timeServer = new TimeServer();
        timeServer.start(12345);
    }

    public void start(int port){
        ServerSocket serverSocket = null;
        try{
            serverSocket = new ServerSocket(port);
            System.out.println("The time server is start in port : " + port);
            Socket socket = null;
            while(true) {
                socket = serverSocket.accept();
                new Thread(new TimeServerHandler(socket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(serverSocket!=null){
                System.out.println("The time server closed");
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                serverSocket = null;
            }
        }
    }

    public final class TimeServerHandler implements Runnable{

        private Socket socket;

        public TimeServerHandler(Socket socket){
            this.socket = socket;
        }
        @Override
        public void run() {
            BufferedReader in = null;
            PrintWriter out = null;
            try{
                in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
                out = new PrintWriter(this.socket.getOutputStream(),true);
                String currentTime = null;
                String body = null;
                while(true){
                    body = in.readLine();
                    if(body == null)
                        break;
                    System.out.println("The time server receive order : " + body + " | active threads count " + Thread.activeCount());
                    currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body) ? LocalDate.now().toString() : "BAD ORDER";
                    out.println(currentTime);
                }
            } catch (IOException e) {
                if (in != null) {
                    try {
                        in.close();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
                if (out != null) {
                    out.close();
                    out = null;
                }
                if (this.socket != null) {
                    try {
                        this.socket.close();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    this.socket = null;
                }

            }
        }
        }
    }

