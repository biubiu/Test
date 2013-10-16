package com.shawn.nio;

import com.google.common.base.Strings;

import java.io.*;
import java.nio.charset.Charset;

/**
 * User: Shawn cao
 * Date: 13-10-8
 * Time: PM6:29
 */
public class InputStreamTest {

    public static void main(String[] args) throws IOException {
        File file = new File("/Users/caocao024/workspace/Test/src/main/java/com/shawn/nio/FilePath.java");
        System.out.println("--" + file.length());
      /* RandomAccessFile randomAccessFile = new RandomAccessFile(file,"r");
        String line = "";
        while((line=randomAccessFile.readLine())!=null){
            System.out.println(line);
        }  */

        /*
        FileInputStream fileInputStream = new FileInputStream(file);
        FileOutputStream fileOutputStream = new FileOutputStream("/Users/caocao024/test.java");
        int m = 0;
        while((m = fileInputStream.read())!=-1){
            System.out.print((char)m);
            fileOutputStream.write(m);
        }
        fileInputStream.close();;
        fileOutputStream.close();  */
        //System.out.println(3&0x000000FF);
        System.out.println((int)0xf3ff);
        System.out.format("%s %s %s %s",(byte)129,(byte)255, (byte)256,(byte)257);


    }
}
