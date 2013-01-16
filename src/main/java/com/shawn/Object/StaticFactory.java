package com.shawn.Object;

import com.google.common.collect.Lists;

import java.util.EnumSet;
import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: caocao024
 * Date: 13-1-15
 * Time: PM10:03
 * To change this template use File | Settings | File Templates.
 */
public class StaticFactory {
    public static void main(String[] args){
        StaticConsObj staticConsObj = StaticConsObj.getNameInstance("Tom");
        StaticConsObj staticConsObj1 = StaticConsObj.getNameInstance("Jack");
        System.out.println(staticConsObj.hashCode() == staticConsObj1.hashCode());
        System.out.println(staticConsObj.getName() + staticConsObj1.getName());
        HashMap<String,String> maps = StaticConsObj.newInstance();
    }
}

class StaticConsObj{

    private String name;
    private static final StaticConsObj CONS_OBJ = new StaticConsObj();

    public static <K,V> HashMap<K,V> newInstance(){
        return new HashMap<K,V>();
    }
    public static StaticConsObj getInstance(){
        return CONS_OBJ;
    }

    public static StaticConsObj getNameInstance(final String name){
        CONS_OBJ.setName(name);
        return CONS_OBJ;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "StaticConsObj{" +
                "name='" + name + '\'' +
                '}';
    }
}