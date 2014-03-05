package com.shawn.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.Set;

/**
 * User: Shawn cao
 * Date: 13-9-12
 * Time: PM11:26
 */
public class FileManipulation {

    public static void main(String[] args) throws IOException {
        filePermission();
        //deleteFile();
        fileCopy();
    }

    private static void filePermission() throws IOException {
        Path target = Paths.get("/Users/caocao024/tmp");
        if(!target.toFile().exists()){
        Set<PosixFilePermission> perms = PosixFilePermissions.fromString("rw-rw-rw-");
        FileAttribute<Set<PosixFilePermission>> attribute = PosixFilePermissions.asFileAttribute(perms);
        Files.createFile(target,attribute);
        }

    }

    private static void deleteFile() throws IOException{
        Path target = Paths.get("/Users/caocao024/tmp");
        Files.delete(target);
    }

    private static void fileCopy() throws  IOException{
        Path source = Paths.get("/Users/caocao024/tmp");
        Path target = Paths.get("/Users/caocao024/temp/tmp");
        Files.move(source,target, StandardCopyOption.ATOMIC_MOVE);
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

