package com.shawn.nio;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.jar.Attributes;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.Pack200;
import java.util.jar.Pack200.Packer;
import java.util.zip.ZipEntry;

import org.joda.time.DateTime;

import com.google.common.base.Strings;

public class JarFileTest {

    public static void main(String[] args) throws IOException {
        //jarLister("/home/shawncao/.m2/repository/joda-time/joda-time/2.3/joda-time-2.3-sources.jar");
        packClass("/home/shawncao/workspace/Test/src");
    }


    public static void jarLister(String filename) throws IOException{
        JarFile jarFile = new JarFile(filename);
        Enumeration<JarEntry> enumeration = jarFile.entries();
        while (enumeration.hasMoreElements()) {
            JarEntry jarEntry = (JarEntry) enumeration.nextElement();
            String name = jarEntry.getName();
            DateTime dateTime = new DateTime(jarEntry.getTime());
            long size = jarEntry.getSize();
            long compressSize = jarEntry.getCompressedSize();
            long crc = jarEntry.getCrc();
            int method = jarEntry.getMethod();
            String comment = jarEntry.getComment();
            switch (method) {
            case ZipEntry.STORED:
                 System.out.println(name + " was stored at " + dateTime.toString("yyyy-MM-dd HH:mm:ss") + " with a size of  " + size+ " bytes");
                 break;
            case ZipEntry.DEFLATED:
                 System.out.println(name + " was deflated at " + dateTime.toString("yyyy-MM-dd HH:mm:ss") +"from  " + size + " bytes to "
                  + compressSize + " bytes, a savings of "
                  + (100.0 - 100.0*compressSize/size) + "%");
                 break;
            default:
                 System.out.println(name  + " was compressed using an unrecognized method at " +  dateTime.toString("yyyy-MM-dd HH:mm:ss")
                        +"from  " + size + " bytes to "
                         + compressSize + " bytes, a savings of "
                         + (100.0 - 100.0*compressSize/size) + "%");
                 break;
            }
              System.out.println("Its CRC is " + crc);
              if (!Strings.isNullOrEmpty(comment)) {
                  System.out.println(comment);
                }
                if (jarEntry.isDirectory( )) {
                  System.out.println(name + " is a directory");
                }
                Attributes a = jarEntry.getAttributes( );
                if (a != null) {
                  Object[] nameValuePairs = a.entrySet().toArray( );
                  for (int j = 0; j < nameValuePairs.length; j++) {
                    System.out.println(nameValuePairs[j]);
                  }
                }
            }
            jarFile.close();
        }

    public static void packClass(String filename) throws FileNotFoundException, IOException{
        Packer packer = Pack200.newPacker();
        FileOutputStream fos = null;
        try {

            packer.pack(new JarFile(filename), fos =new FileOutputStream(filename+".jar"));
        }finally{
            if(fos!=null) fos.close();
        }


    }
}
