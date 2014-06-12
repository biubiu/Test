package com.shawn.guava;


import com.google.common.collect.*;
import org.junit.Test;


import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import static org.junit.Assert.assertTrue;

/**
 * User: Shawn cao
 * Date: 14-6-3
 * Time: AM11:36
 */
public class CollectionTest {

    @Test
    public void multiMapList(){
        Multimap<Integer,People> peopleMultiMap = ArrayListMultimap.create();
        peopleMultiMap.put(1, new PeopleBuilder().withName("andrew").withTitle("Class").create());
        peopleMultiMap. put(1, new PeopleBuilder().withName("Bob").withTitle("Leader").create());

        Collection<People> peoples = peopleMultiMap.get(1);
        assertTrue(peoples.size() > 1);
        assertTrue(peoples.iterator().next().getName().equals("andrew"));
    }


    @Test
    public void multiMapmap(){
        Multimap<Integer,People> peopleMultimap = HashMultimap.create();
        peopleMultimap.put(1, new PeopleBuilder().withName("andrew").withTitle("Class").create());
        peopleMultimap. put(1, new PeopleBuilder().withName("Bob").withTitle("Leader").create());
        Collection<People> peoples = peopleMultimap.get(1);
        assertTrue(peoples.size() > 1);
        assertTrue(peoples.iterator().next().getName().equals("andrew"));
    }


    @Test
    public void multiSet(){

    }

    @Test
    public void reflection() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        People people = new PeopleBuilder().withName("John").withTitle(" Alias").create();
        Class clazz = people.getClass();
        Field[] fields = clazz.getDeclaredFields();

        System.out.println("size: " + fields.length);
        for(Field field: fields ){
            System.out.println(field.getName());
            //Method method = clazz.getMethod("get"+field.getName(),String.class);
            //String value = (String) method.invoke(people,new Object[]{});
            field.setAccessible(true);
            System.out.println(field.get(people));
        }
    }


    @Test
    public void testTable(){
        Table<String, Integer, String> table = HashBasedTable.create();
        for(char a='A'; a <= 'C'; a++){
            for(int b = 1;b <= 4 ; b++){
                table.put(Character.toString(a),b,String.format("%c%d",a,b));
            }
        }

        System.out.println(table.column(1));
        System.out.println(table.column(2));
        System.out.println(table.column(3));
        System.out.println(table.column(4));
        System.out.println(table.get("A",3));
        System.out.println(table.row("A"));
    }

    @Test
    public void testClassToInstanceMap(){
        ClassToInstanceMap<String> classToInstanceMapString = MutableClassToInstanceMap.create();
        ClassToInstanceMap<People> personClassToInstanceMapPerson = MutableClassToInstanceMap.create();
        People people = new PeopleBuilder().withTitle("Sir").withName("Alex").create();
        classToInstanceMapString.put(String.class,"Alex");
        personClassToInstanceMapPerson.putInstance(People.class, people);
        assertTrue("Alex".equals(classToInstanceMapString.getInstance(String.class)));
        assertTrue(people.equals(personClassToInstanceMapPerson.getInstance(People.class)));

    }

    /**
     * Key-Value Map Impl     Value-Key Map Impl     Corresponding BiMap
     　　HashMap                 　　  HashMap                       HashBiMap
     　　ImmutableMap           　　ImmutableMap               ImmutableBiMap
     　　EnumMap                　　  EnumMap                      EnumBiMap
     　　EnumMap                 　　 HashMap                      EnumHashBiMap
     */

    @Test
    public void testBiMap(){
        BiMap<Integer,String> bimap = HashBiMap.create();
        bimap.put(1,"a1.log");
        bimap.put(2,"a2.log");
        bimap.put(3,"a3.log");
        bimap.put(4,"a4.log");

        assertTrue(bimap.get(2).equals("a2.log"));
        assertTrue(bimap.inverse().get("a2.log").equals(2));
        assertTrue(bimap.get(3).equals("a3.log"));
    }


    @Test(expected=IllegalArgumentException.class)
    public void testBiMapValueConsistent(){
        BiMap<Integer,String> bimap = HashBiMap.create();
        bimap.put(1,"a1.log");
        bimap.put(2,"a2.log");
        bimap.put(3,"a3.log");
        bimap.put(4,"a4.log");
        bimap.put(5,"a4.log");
        //bimap.forcePut(5,"a4.log");
        //assertTrue(bimap.get("a4.log").equals(5));

    }



    class People{
        private String name;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        private String title;


        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "People{" +
                    "name='" + name + '\'' +
                    ", title='" + title + '\'' +
                    '}';
        }
    }

    public class PeopleBuilder{

        private People instance;

        PeopleBuilder(){
            instance = new People();
        }

        private PeopleBuilder withName(String name){
            instance.setName(name);
            return this;
        }

        private PeopleBuilder withTitle(String title){
            instance.setTitle(title);
            return this;
        }

        private People create(){
            return this.instance;
        }
    }

}
