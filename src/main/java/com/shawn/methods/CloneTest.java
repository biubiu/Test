package com.shawn.methods;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Calendar;
import java.util.LinkedList;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.google.common.collect.Lists;
import com.shawn.methods.DeepCloneSample.DeepCloneInner;
import com.shawn.object.StaticFactory;

public class CloneTest {
    public static void main(String[] args) {
        ShallowCloneSample s1 = new ShallowCloneSample(10, "james");
        ShallowCloneSample s2 = (ShallowCloneSample) s1.clone();
        /*System.out.println( s1.toString() + "| " + s2.toString());
        System.out.println("equals: "+s1.equals(s2) + " class:" + (s1.getClass()==s2.getClass()) );*/

        final StringBuilder sb = new StringBuilder();

        DeepCloneInner dci1 = new DeepCloneInner();
        Calendar c1 = Calendar.getInstance();
        c1.set(1987, 2, 24);
        s1.setAge(200);
        LinkedList<ShallowCloneSample> ls1 = Lists.newLinkedList(Arrays.asList(s1,s2));
        dci1.setCalendar(c1);
        dci1.setSamples(ls1);
        //with shallow copy of s1, since refer of s2 to s1 is not changed ,so s2 is still the same value as s1
        DeepCloneSample ds1 = new DeepCloneSample();
        ds1.setNames(sb.append("ds1"));
        ds1.setInner(dci1);
        DeepCloneSample ds2 = (DeepCloneSample)ds1.clone();

        ds2.getInner().getSamples().get(0).setName("Tommy");
        System.out.println(ds1.getInner().getSamples().toString());
        System.out.println(ds2.getInner().getSamples().toString());
    }



}

class ShallowCloneSample implements Cloneable ,Serializable{
    /**
     *
     */
    private static final long serialVersionUID = 4305142643372301749L;
    private int age;
    private String name;
    public ShallowCloneSample(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //shallow copy
    @Override
    protected Object clone(){
        Object object = null;
        try{
           object =  super.clone();
        }catch(CloneNotSupportedException e){

        }
        return object;
    }

    /*@Override
    protected Object clone(){
        Object object = null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ByteArrayInputStream bais =null;
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(baos);
            oos.writeObject(this);
        } catch (IOException e) {
            e.printStackTrace();
        }

        bais= new ByteArrayInputStream(baos.toByteArray());
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(bais);
            object = ois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return object;
    }*/
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}

class DeepCloneSample implements Cloneable,Serializable{
    /**
     *
     */
    private static final long serialVersionUID = -6330713682745426658L;
    private DeepCloneInner inner;
    private StringBuilder names;

    public DeepCloneInner getInner() {
        return inner;
    }

    public void setInner(DeepCloneInner inner) {
        this.inner = inner;
    }

    public StringBuilder getNames() {
        return names;
    }

    public void setNames(StringBuilder names) {
        this.names = names;
    }

    static class DeepCloneInner implements Serializable {
        private static final long serialVersionUID = -3406600866943723451L;
        private LinkedList<ShallowCloneSample> samples;
        private Calendar calendar;

        public LinkedList<ShallowCloneSample> getSamples() {
            return samples;
        }

        public void setSamples(LinkedList<ShallowCloneSample> samples) {
            this.samples = samples;
        }

        public Calendar getCalendar() {
            return calendar;
        }

        public void setCalendar(Calendar calendar) {
            this.calendar = calendar;
        }

        @Override
        public String toString() {
            return ToStringBuilder.reflectionToString(this);
        }
    }

    @Override
    protected Object clone(){
        Object object = null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ByteArrayInputStream bais =null;
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(baos);
            oos.writeObject(this);
        } catch (IOException e) {
            e.printStackTrace();
        }

        bais= new ByteArrayInputStream(baos.toByteArray());
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(bais);
            object = ois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return object;
    }
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
