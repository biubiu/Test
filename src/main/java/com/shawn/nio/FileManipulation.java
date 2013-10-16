package com.shawn.nio;

import java.io.IOException;
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
}

