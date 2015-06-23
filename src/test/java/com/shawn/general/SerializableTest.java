package com.shawn.general;


import com.google.common.collect.Lists;

import java.io.*;


/**
 * @author Shawn Cao
 */
public class SerializableTest {

    private static String path;
    public static void main(String[] args) throws Exception{
        path = SerializableTest.class.getResource("obj").getPath();
        System.out.print(path);
        writeObj();
        readObj();

    }

    private static void writeObj() throws Exception{
        SerializeObject object = new SerializeObject(Lists.newArrayList("a","b","c","d"),"no1",false);
        ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(path));
        stream.writeObject(object);
        stream.close();
    }

    private static void readObj() throws Exception{
        ObjectInputStream stream = new ObjectInputStream(new FileInputStream(path));
        Object obj = stream.readObject();
        stream.close();
        System.out.println(((SerializeObject)obj).toString());
    }

}




