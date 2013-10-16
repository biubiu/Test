package com.shawn.nio;

import com.google.common.base.Splitter;
import com.google.common.collect.Queues;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * User: Shawn cao
 * Date: 13-10-12
 * Time: PM2:58
 */
public class FileDumper {

    private static final  int ASC=0;
    private static final int DEC = 1;
    private static final int HEX=2;
    public static void main(String[] args) throws IOException {
        if(args.length <1){
            System.err.println("Usage: java FileDumper [-ahd] filename");
            return ;
        }

        int firstArg = 0;
        int mode = ASC;
        if(args[0].startsWith("-")){
            firstArg = 1;
            if(args[0].equals("-h"))
                mode = HEX;
            else if (args[0].equals("-d")) mode = DEC;
        }

        for (int i = firstArg;i<args.length;i++){
            try {
                if(mode==ASC) dumpAscii(args[i]);
                if(mode == HEX) dumpHex(args[i]);
                if (mode == DEC) dumpDecimal(args[i]);
            }catch (IOException e){
                System.err.println("Error reading from " + args[i] + " :" + e.getMessage());
            }
        }
        //String fileCreated = typeFile(args[0]);
        //deleteFile(fileCreated);
    }

    private static void dumpAscii(String filename) throws IOException{
         FileInputStream fin = null;
        try {
            fin = new FileInputStream(filename);
            copy(fin,System.out);
        }finally {
            if(fin!=null) fin.close();
        }
    }
    private static void dumpHex(String filename) throws IOException{
        FileInputStream fin = null;
        byte[] buffer = new byte[24];
        boolean end = false;
        try {
            fin = new FileInputStream(filename);
            while(!end){
                int bytesRead = 0;
                while(bytesRead < buffer.length){
                       int read = fin.read(buffer,bytesRead,buffer.length-bytesRead);
                    if(read==-1){
                        end = true;
                        break;
                    }
                    bytesRead+=read;
                }
                for (int i=0;i<bytesRead;i++){
                    int hex = buffer[i];
                    if(hex <0) hex = 256+hex;
                    if(hex >=16){
                        System.out.print(Integer.toHexString(hex) + " ");
                    }else {
                        System.out.print("0"+Integer.toHexString(hex)+" ");
                    }
                }
                System.out.println();
            }
        }finally {
            if(fin != null) fin.close();
        }
    }
    private static void dumpDecimal(String filename) throws IOException{
      FileInputStream fin = null;
        byte[] buffer = new byte[16];
        boolean end = false;
        try {
         fin = new FileInputStream(filename);
            while(!end){
                int bytesRead = 0;
                while(bytesRead < buffer.length){
                   int read = fin.read(buffer,bytesRead,buffer.length-bytesRead);
                    if(read == -1){
                        end = true;
                        break;
                    }
                    bytesRead+=read;
                }
                for(int i=0;i<bytesRead;i++){
                    int dec = buffer[i];
                    if(dec<0) dec = 256+dec;
                    if(dec<10){
                        System.out.print("000"+dec+" ");
                    }else if(dec<100){
                        System.out.print("00"+dec+" ");
                    }else{
                        System.out.print("0"+dec+" ");
                    }
                }
                System.out.println();
            }
        }finally {
            if(fin != null) fin.close();
        }
    }
    private static void deleteFile(String path){
        Path filePath = Paths.get(path);
        filePath.toFile().delete();
    }



    private static String typeFile(String filename) throws IOException{

        String name = Queues.newArrayDeque(Splitter.on("/").split(filename)).peekLast();
        String path = "";
        FileInputStream fin=null;
        FileOutputStream fos = null;
        try {
             fin= new FileInputStream(filename);
            path = filename.replace(name,name.concat("_copy"));
            fos= new FileOutputStream(path);
            copy(fin,fos);

        } catch (FileNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } finally {
             if(fin!=null) fin.close();
            if(fos!=null) fos.close();
        }
        return  path;
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
