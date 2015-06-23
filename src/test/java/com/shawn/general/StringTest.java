package com.shawn.general;

import com.google.common.base.Charsets;
import org.junit.Test;
import org.springframework.web.util.UriUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * User: Shawn cao
 * Date: 14-5-26
 * Time: AM10:25
 */
public class StringTest {

    @Test
    public void testImmutability(){
         String str1 = "aaa";
         System.out.println(str1.hashCode());
        str1 = "bbb";
        System.out.println(str1.hashCode());
        String str2 = "aaa";
        System.out.println(str2.hashCode());

    }

    @Test
    public void testUrlEncoding() throws UnsupportedEncodingException {
        String query = URLEncoder.encode("sourceLink=+http://www.51zhucai.com/zhiwei/job167307.html&applyId=123", Charsets.UTF_8.name());
        System.out.println(query);
        String tran = URLDecoder.decode(query,Charsets.UTF_8.name());
        System.out.println(tran);
    }
    @Test
    public void testBitShift(){
        int i = 2118123;
        System.out.println((i>>31)&1);
    }

    public static String stringReplace(String text){
        text = text.replace("j","l");
        return text;
    }

    public static void bufferReplace(StringBuffer text){
        text = text.append("c");
    }

    public static void main(String args[]){
        String textStr = "java";
        StringBuffer textBuffer = new StringBuffer("java");
        System.out.println("b---" + textBuffer.hashCode());
        bufferReplace(textBuffer);

        System.out.println(stringReplace(textStr) + " | " + textStr.hashCode());
        System.out.println(textBuffer  +" "+ textBuffer.hashCode());
    }
}
