package com.shawn.general;

import org.junit.Test;
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
