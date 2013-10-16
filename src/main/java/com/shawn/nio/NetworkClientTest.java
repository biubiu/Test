package com.shawn.nio;

import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * User: Shawn cao
 * Date: 13-10-12
 * Time: PM5:35
 */
public class NetworkClientTest {
    public static void main(String[] args){
         if(args.length == 0){
             System.err.println("Usage: java Mail Client username@host.com");
             return;
         }
        try{
            URL url = new URL("mailto:" + args[0]);
            URLConnection uc = url.openConnection();
            uc.setDoOutput(true);
            uc.connect();
            OutputStream out = uc.getOutputStream();
            for(int c = System.in.read(); c!=-1;c=System.in.read()){
                out.write(c);
            }
            out.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}
