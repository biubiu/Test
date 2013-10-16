package com.shawn.nio;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

import com.google.common.io.Files;

public class nioTest {

    public static void main(String[] args) throws IOException {
        fileToPath("/home/shawncao/workspace/Test/target/surefire/", 12345, 64);
        //deleteFile(new StringBuilder("/home/shawncao/workspace/oldTest/target/surefire"));
        //copyFile(new File("/home/shawncao/workspace/oldTest/pom.xml"), "/home/shawncao/workspace/oldTest/target/surefire/");
    }

    public static void deleteFile(StringBuilder filePath){
         new File(filePath.toString()).listFiles(new FileFilter() {
             public boolean accept(File file) {
                 if(file.isFile()){
                     file.delete();
                     return true;
                 }
                 return false;
             }
         });
    }
    public static void fileToPath(String attachmentStoreParentPath,int userId,int snapshotId) throws IOException{
        StringBuilder path = new StringBuilder(attachmentStoreParentPath);
        path.append("/").append(userId).toString();
        System.out.println("parh: " + path.toString());
        File[] originals=new File(path.toString()).listFiles(new FileFilter() {
            public boolean accept(File file) {
                if(file.isFile())
                    return true;
                return false;
            }
        });
        System.out.println("originals: " + (originals==null) + " | "+originals.length);
        if(originals.length>0){
            File target = new File(path.append("/").append(snapshotId).toString());
            if(!target.exists()){
                target.mkdirs();
            }
            Files.copy(originals[0], new File(path.append("/").append(originals[0].getName()).toString()));
        }
    }

    public static void copyFile(File sourceFile,String toPath) throws IOException{

        File desFile = new File(toPath+"/"+sourceFile.getName());

        FileChannel source = null;
        FileChannel destination = null;
        try {
            source = new FileInputStream(sourceFile).getChannel();
            destination = new FileOutputStream(desFile).getChannel();
            long count = 0;
            long size = source.size();
            System.out.println("11: " + size);
            while((count+=destination.transferFrom(source, count, size -count))<size){}
        } finally{
            if(source!=null){
                source.close();
            }
            if(destination!=null){
                destination.close();
            }
        }
    }
}
