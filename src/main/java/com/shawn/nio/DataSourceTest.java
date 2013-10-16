package com.shawn.nio;

import java.io.*;
import java.nio.file.Paths;

/**
 * User: Shawn cao
 * Date: 13-10-9
 * Time: PM5:36
 */
public class DataSourceTest{

    public static void main(String[] args) throws IOException {
              //printFile();
              writeFile(new FileInputStream(Paths.get("/etc/hosts").toFile()));
    }

    private static void printFile() throws IOException {
        File[] roots = File.listRoots();
        File dir = new File(roots[0],"etc");
        File hosts = new File(dir,"hosts");
        FileInputStream fis = new FileInputStream(hosts);
        byte[] buffer = new byte[(int) hosts.length()];
        int mark = 0;
        while((mark = fis.read(buffer))!=-1){
        }
        for(int i = 0 ; i <  buffer.length;i++){
            System.out.print((char)buffer[i]);
        }
    }

    private static void writeFile(InputStream inputStream) throws IOException{
        File[] roots = File.listRoots();
        File dir = new File(roots[0],"/Users/caocao024");
        File hosts = new File(dir,"hosts_qa");

        FileOutputStream outputStream = new FileOutputStream(hosts,true);
        byte[] buffer = new byte[1024];
        while(true){
            int bytesRead = inputStream.read(buffer);
            if(bytesRead == -1) break;
            else outputStream.write(buffer,0,bytesRead);
        }
    }
}
