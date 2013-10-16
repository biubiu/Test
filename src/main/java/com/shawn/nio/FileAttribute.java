package com.shawn.nio;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.PosixFileAttributes;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.Set;

/**
 * User: Shawn cao
 * Date: 13-9-13
 * Time: AM12:14
 */
public class FileAttribute {
    public static void main(String[] args) throws IOException {
                 //readAttribute();
        //setAttribute();
        readSymbolic();

    }

    private static void setAttribute() throws IOException {
        Path target = Paths.get("/Users/caocao024/tmp");
        PosixFileAttributes attributes = Files.readAttributes(target,PosixFileAttributes.class);
        Set<PosixFilePermission> posixFilePermission = attributes.permissions();
        posixFilePermission.clear();
        String owner = attributes.owner().getName();
        String perms = PosixFilePermissions.toString(posixFilePermission);
        System.out.format("%s %s %n",owner,perms);

        posixFilePermission.add(PosixFilePermission.OWNER_READ);
        posixFilePermission.add(PosixFilePermission.GROUP_READ);
        posixFilePermission.add(PosixFilePermission.OTHERS_READ);
        posixFilePermission.add(PosixFilePermission.OWNER_WRITE);
        Files.setPosixFilePermissions(target,posixFilePermission);

    }
    private static void readAttribute(){
        try{
        Path zip = Paths.get("/usr/bin/zip");
        System.out.println(Files.getLastModifiedTime(zip));
        System.out.println(Files.size(zip));
        System.out.println(Files.isSymbolicLink(zip));
        System.out.println(Files.isDirectory(zip));
        System.out.println(Files.readAttributes(zip,"*"));
        }catch(IOException e){

        }
    }

    private static void readSymbolic(){
        Path file=Paths.get("/usr/bin/scala");
        try{
            if(Files.isSymbolicLink(file)){
                file = Files.readSymbolicLink(file);
            }
            BasicFileAttributes attributes = Files.readAttributes(file, BasicFileAttributes.class);
            System.out.println("---"+attributes.creationTime());
        }catch (IOException e){
           e.printStackTrace();
        }

    }
}
