package com.shawn.nio;

import sun.security.util.BigInt;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class DigestTest {
    public static void main(String args[]) throws IOException, NoSuchAlgorithmException {
        digest("http://www.oreilly.com");
    }

   private static String digest(String urlAdd) throws IOException, NoSuchAlgorithmException {
       URL url = new URL(urlAdd);
       InputStream inputStream = url.openStream();
       MessageDigest digest = MessageDigest.getInstance("MD5");
       byte[] data = new byte[128];
        while(true){
            int byteRead = inputStream.read(data);
            if(byteRead <0 ) break;
            System.out.println("byte reads: " + byteRead);
            digest.update(data,0,byteRead);
        }
       byte[] result = digest.digest();
       for (int i=0; i<result.length;i++){
           System.out.print(result[i]+"\t");
       }
       System.out.println("\n" +new BigInteger(result));
       return new String(result);
    }
}
