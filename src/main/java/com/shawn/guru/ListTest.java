package com.shawn.guru;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.google.common.base.CharMatcher;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multiset;
import com.google.common.collect.Multisets;
import com.google.common.collect.Sets;

public class ListTest {

    public static void main(String[] args) {


        Person p1 = new Person("aaa", "1", "tianji");
        Person p2 = new Person("b", "2", "haha");
        Person p3 = new Person("c", "3", "tianji");
        Person p4 = new Person("d", "4", "dia");
        Person p5 = new Person("e", "5", "haha");
        Person p6 = new Person("f", "6", "dia");
        Person p7 = new Person("g", "7", "tianji");
        Person p8 = new Person("h", "8", "haha");
        List<Person> list = Arrays.asList(p1,p2,p3,p4,p5,p6,p7,p8);
        List<Person> others = Lists.newArrayList();
        List<Person> tianjis = Lists.newArrayList();
        for(Person p:list){
            if(p.getFrom().equals("tianji")){
                tianjis.add(p);
            }else{
                others.add(p);
            }
        }

        //List<Person> tianjis= map.get("tianji");
        for (Person p: tianjis) {
            p.setOthers("tianji");
        }
        //List<Person> diasList = map.get("dia");
        for (Person p:others) {
            p.setOthers("other");
        }

        //List<Person> result = Lists.newArrayList(map.values());
        for (Person p: list) {
            System.out.println(p.toString());
        }

        System.out.println(CharMatcher.inRange('a', 'z').or(CharMatcher.inRange('A', 'Z')).matchesAllOf("asdasdasd"));
    }


}

class Person{
    String name;
    String id;
    String from;
    String others;
    public Person(String name, String id, String from) {
        this.name = name;
        this.id = id;
        this.from = from;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getFrom() {
        return from;
    }
    public void setFrom(String from) {
        this.from = from;
    }
    public String getOthers() {
        return others;
    }
    public void setOthers(String others) {
        this.others = others;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
