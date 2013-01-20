package com.shawn.object;

import java.util.HashMap;

import com.google.common.collect.ImmutableList;

/**
 * Created with IntelliJ IDEA. User: caocao024 Date: 13-1-15 Time: PM10:03 To
 * change this template use File | Settings | File Templates.
 */
public class StaticFactory {
    public static void main(String[] args) {

        /**
         * static factory 1.initialize object with name "new StaticConsObj()"---->"StaticConsObj.getNameInstance()"
         * 				   2.not required to create a new object each time they're invoked-->instance controlled
         */
        StaticConsObj staticConsObj = StaticConsObj.getNameInstance("Tom");
        StaticConsObj staticConsObj1 = StaticConsObj.getNameInstance("Jack");
        System.out.println(staticConsObj.hashCode() == staticConsObj1.hashCode());
        System.out.println(staticConsObj.getName() + staticConsObj1.getName());
        /**
         * 3.return an object of any subtype their return type (also con.),see:
         * 			ImmutableList: a.doesn't expose any public or protected cons, it can be
         * 						 subclassed within the package while not allowing user to subclass it
         * 						 b.return specialized subclass without exposing their types
         * 						 c.of() return the singlton instance of EmptyImmutableList , of(E) return RegularImmutableList
         */
        ImmutableList immutableList = ImmutableList.of(1, 2, 3, 4);

        /**
         * create new object with specifying the parameterized type ,con:
         * HashMap<String,String> maps = new HashMap<String,String>();
         */

        HashMap<String, String> maps = StaticConsObj.newInstance();
        /**
         * valueOf---type-conversion e.g. Boolean.valueOf();
         * 		of---concise alternative to valueOf
         * 		getInstance--returns an instance described by parameters cannot be said to have same value(sole instance)
         * 	 	newInstance---like getInstance but returned instance is distinct
         * 		getType---like getInstance when factory method is in a different class
         * 		newType---like newInstance
         */
    }
}

class StaticConsObj {

    private String name;
    private static final StaticConsObj CONS_OBJ = new StaticConsObj();

    public static <K, V> HashMap<K, V> newInstance() {
        return new HashMap<K, V>();
    }

    public static StaticConsObj getInstance() {
        return CONS_OBJ;
    }

    public static StaticConsObj getNameInstance(final String name) {
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
        return "StaticConsObj{" + "name='" + name + '\'' + '}';
    }
}