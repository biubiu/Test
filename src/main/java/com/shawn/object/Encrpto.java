package com.shawn.object;

import java.util.jar.JarFile;

import org.apache.commons.codec.binary.Base64;

public class Encrpto {

    public static void main(String[] args) throws Exception {
        byte[] oringinals = "r_65498_101.doc".getBytes("UTF-8");
        byte[] encoded = Base64.encodeBase64(oringinals);
        System.out.println("encoded: " + new String(encoded));
        System.out.println("decoded: "+ new String(Base64.decodeBase64(encoded)));

    }
}
