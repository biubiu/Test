package com.shawn.general;

import java.io.UnsupportedEncodingException;

import java.net.URLDecoder;

public class URLDocode {
    public static void main(String[] args) throws UnsupportedEncodingException {
        String decode = "keyword%3A%28%28%22js%E5%B7%A5%E7%A8%8B%E5%B8%88%22+%29%29+AND+flag%3A%28s+OR+t+OR+r%29&start=0&rows=100&sort=modifyTime+desc%2Cscore+desc";
        System.out.println("decoded: ");
        System.out.println(new URLDecoder().decode(decode, "UTF-8").toString());
    }
}
