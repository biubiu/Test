package com.shawn.nio;

import java.io.*;
import java.util.Scanner;

/**
 * User: Shawn cao
 * Date: 13-10-1
 * Time: PM4:28
 */
public class CharTest {

    public static void main(String[] args) throws IOException {
        char copyright = 169;
        copyright = '\u00A9';
        copyright='a'+'b';
        //System.out.format("%s %s %s %s",copyright,(int)copyright ,(int)'a',(int)'b');
        //writerByByteArr();
        //writeBychar();
        //readByByte();
        //readByChunkByte();
        //readByChunk();
        //readAvailible();
        //markWord();;
          copy(System.in,System.out);
    }

    private static void writeBychar(){
        for (int i =32; i<127;i++){
            System.out.write(i);
            if(i%8 == 7)
                System.out.write(10);
            else
                System.out.write(9);
        }
        System.out.write('\n');
    }

    private static void writerByByteArr(){
        byte[] bytes = new byte[(127-31)*2];
        int index = 0;
        for(int i=32;i<127;i++){
            bytes[index++] = (byte)i;
            if(i%8 == 7){
                bytes[index++]=(byte)'\n';
            }
            else {
                bytes[index++] = (byte)'\t';
            }
        }
        bytes[index++] = (byte)'\n';
        try {
            System.out.write(bytes);

        } catch (IOException e) {
            System.err.println(e);
        }
    }

    private static void readByByte(){
        byte[] b = new byte[10];
        for(int i = 0; i< b.length;i++){
            try {
                b[i] = (byte)System.in.read();
                System.out.print("b" + (b[i] >=0?b[i]:256+b[i]));
            } catch (IOException e) {
                 System.err.print(e);
            }
        }
    }

    private static void readByChunkByte(){
        try {
           byte[] b = new byte[10];
            System.in.read(b);
            for (int i = 0; i< b.length;i++)
                System.out.print((char)b[i]+ " ");
        } catch (IOException e) {
            System.err.print(e);
        }
    }

    private static void readByChunk(){
        byte[] b = new byte[10];
        int offset = 0;
        while(offset < b.length){
            int bytesRead = 0;
            try {
                bytesRead = System.in.read(b,offset,b.length-offset);
            } catch (IOException e) {
                System.err.print(e);
            }
            if(bytesRead == -1) break;
            offset += bytesRead;
        }

        for (int i = 0; i< b.length;i++)
            System.out.print((char)b[i]+ " ");
    }

    private static void readAvailible(){
        try {
           byte[] b = new byte[System.in.available()];
            System.in.read(b);
            for (int i = 0; i< b.length;i++)
                System.out.print((char)b[i]+ ",");
        }catch (IOException e) {
            System.err.print(e);
        }
    }

    private static void markWord(){
        File file = new File("/Users/caocao024/workspace/Test/src/main/java/com/shawn/nio/FilePath.java");
        try {
            BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
            if(bufferedInputStream.markSupported()){
                int i = 0;
                while((i=bufferedInputStream.read())!=-1){
                    if((char)i == '>'){
                        System.out.println("---->>>> " + (char)i);
                        bufferedInputStream.mark(10);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.err.print(e);
        } catch (IOException e) {
            System.err.print(e);
        }
    }

    private static void copy(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[1024];
        while(true){
            int bytesRead = in.read(buffer);
            if(bytesRead == -1) break;
            else out.write(buffer,0,bytesRead);
        }
    }
}
