package com.shawn.general;

public class StringTest {

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
