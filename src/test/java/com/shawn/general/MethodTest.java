package com.shawn.general;

import java.util.*;

/**
 * @author Shawn Cao
 */
public class MethodTest {

    public static void main(String[] args){
        //new MethodA().m1(-1);
        //removeElement();
        List<String> list = new ArrayList<>();
        list.add("aaaa");
        list.remove(new Integer(0));
        System.out.println(list);


    }


    public static void removeElement(){
        Set<Integer> set = new TreeSet<>();
        List<Integer> list = new ArrayList<>();

        for(int i = -3; i<3; i++){
            set.add(i);
            list.add(i);
        }

        for(int i= 0; i< 3; i++){
            set.remove(i);
            list.remove((Integer)i);
        }

        System.out.println(set + " " + list);
    }
}


class MethodA{

    public void m1(int a){
            m2(a, "a");
    }

    private void m2(int a,String b){
        assert a>=0;
        assert b!=null;

        System.out.println(10/a + " " + b);
    }
}


