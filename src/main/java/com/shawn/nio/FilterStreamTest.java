package com.shawn.nio;

import java.io.*;
import java.nio.file.Paths;
import java.util.jar.JarFile;

/**
 * User: Shawn cao
 * Date: 13-10-13
 * Time: PM10:06
 */
public class FilterStreamTest {

    public static void main(String[] args) throws IOException {
            File file = new File("/Users/caocao024/workspace/Test/src/main/java/com/shawn/nio/FilterStreamTest.java");
            copy(new FileInputStream(file),System.out);
    }

    public static void copy(InputStream in,OutputStream out) throws IOException {
        BufferedInputStream bufferedInputStream = new BufferedInputStream(in);
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(out);
        while(true){
            int datum = bufferedInputStream.read();
            if(datum!=-1){
                bufferedOutputStream.write(datum);
                bufferedOutputStream.flush();
            }else {
                break;
            }

        }
    }
}
