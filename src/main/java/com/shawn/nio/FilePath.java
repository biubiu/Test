package com.shawn.nio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * Created with IntelliJ IDEA.
 * User: caocao024
 * Date: 13-9-12
 * Time: PM10:03
 * To change this template use File | Settings | File Templates.
 */
public class FilePath {

    public static void main(String[] args) throws IOException {
     /*   Path listing = Paths.get("/usr/bin/zip");
        System.out.println(listing.getRoot()+" | " + listing.getParent() + " | "+listing.getNameCount());
        Path normalizedPath = Paths.get("./FilePath.java").normalize();
        System.out.println(normalizedPath.toString());
        Path dir = Paths.get("/Users/caocao024/workspace/tianji-job");
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir,"*.xml")){
            for(Path entry:stream)
                System.out.println(entry.getFileName());
        }catch (IOException e){
            System.out.println(e.getMessage());
        }   */

        //Path startingDir = Paths.get("/Users/caocao024/workspace/tianji-job");
        //Files.walkFileTree(startingDir,new FindPomVisitor());
        Path dir = Paths.get("/Users/caocao024/Downloads");
        Files.walkFileTree(dir,new FindVedioMove("/Users/caocao024/Downloads/.r"));
    }

    private static class FindPomVisitor extends SimpleFileVisitor<Path>{

        @Override
        public FileVisitResult visitFile(Path file,BasicFileAttributes attributes){

            if(file.toString().endsWith(".xml")){
                System.out.println(file.getFileName());
            }
            return FileVisitResult.CONTINUE;
        }
    }

    private static class FindVedioMove extends SimpleFileVisitor<Path>{
        String movePath;
        FindVedioMove(String movePath){
            this.movePath = movePath;
        }
        @Override
        public FileVisitResult visitFile(Path file,BasicFileAttributes attributes) throws IOException {

            if(file.toString().endsWith(".mp4")||file.toString().endsWith(".wmv")){
                Files.createFile(Paths.get(movePath + "/" + file.getFileName()));
                Files.move(file,Paths.get(movePath + "/" + file.getFileName()),StandardCopyOption.REPLACE_EXISTING);
                //Files.delete(file);
            }
            return FileVisitResult.CONTINUE;
        }
    }
}



