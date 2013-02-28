package com.shawn.methods;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
/**
 *
 * @author shawncao
 *never to export two overloadings with the same number of parameters
 */
public class OverloadingTricks {
    public static void main(String[] args) {
        //wineTest();
        classifyTest();
        primitiveAutoBox();
    }

    public static void wineTest(){
        Wine[] wines = {new Wine(),new SparklingWine(),new Champagne()};
        for (int i = 0; i < wines.length; i++) {
            System.out.println(wines[i].name());
        }
    }
    public static void classifyTest() {
        Collection<?>[] collections = { new HashSet<String>(), new ArrayList<String>() };
        for (Collection<?> c : collections) {
            //System.out.println(CollectionClassifier.classify(c));
              System.out.println(CollectionClassifier.classifyRight(c));
        }
    }

    public static void primitiveAutoBox(){
        Set<Integer> set = new TreeSet<Integer>();
        List<Integer> list = new ArrayList<Integer>();

        for(int i=-3; i<3;i++){
            set.add(i);
            list.add(i);
        }
        for(int i=-3; i<3;i++){
            set.remove(i);
            /**
             * list has two remove methods,remove(Object),remove(int index)
             */
            //list.remove(i);
            list.remove(Integer.valueOf(i));
        }
        System.out.println(set+ " " + list);
    }

}

/**
 * classify method is overloaded,choice of which overloading to invoke is made
 * at compile time cuz the classify is static,while selection among overridden
 * is dynamic.main test ,all these types are the same Collection<?> at compile
 * time correct version of overridden method is chosen at runtime
 */

class CollectionClassifier {
    public static String classify(Set<?> set) {
        return "Set";
    }

    public static String classify(List<?> list) {
        return "List";
    }

    public static String classify(Map<?, ?> map) {
        return "Map";
    }

    public static String classify(Collection<?> collection) {
        return "Collection";
    }

    public static String classifyRight(Collection<?> collection){
        return collection instanceof Set ? "Set":collection instanceof List?"List" : "Unknown Collection";
    }
}

class Wine {
    String name() {
        return "wine";
    }
}

class SparklingWine extends Wine {
    @Override
    String name() {
        return "Sparkling wine";
    }
}

class Champagne extends SparklingWine {
    @Override
    String name() {
        return "champagne";
    }
}
