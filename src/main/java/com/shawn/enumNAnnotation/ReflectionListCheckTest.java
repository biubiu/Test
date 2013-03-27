package com.shawn.enumNAnnotation;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.google.common.base.Strings;

/**
 * User: Shawn cao Date: 13-3-24 Time: PM6:51
 */
public class ReflectionListCheckTest {

    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException {

        // set model from rest
        User resume = new User();

         //resume.setEmail("fzxwolf@sina.com");
      //  resume.setUserName("fzx");
      //  resume.setPassword("123456");


        User.Work work1 = new User.Work();
        work1.setName("work1");
        work1.setPosition("position1");
        work1.setStart(new Date());
        work1.setEnd(new Date());

        User.Work work2 = new User.Work();
        work2.setName("work2");
        work2.setPosition("position2");
        work2.setStart(new Date());
        work1.setEnd(new Date());

        List<User.Work> works = new ArrayList<User.Work>();
        works.add(work1);
        works.add(work2);

        User.Edu edu1 = new User.Edu();
        edu1.setName("edu1");
        edu1.setDegree(3);
        edu1.setStart(new Date());
        edu1.setEnd(new Date());

        User.Edu edu2 = new User.Edu();
        edu2.setName("edu2");
        edu2.setDegree(2);
        edu2.setStart(new Date());
        edu2.setEnd(new Date());

        List<User.Edu> edus = new ArrayList<User.Edu>();
        edus.add(edu1);
        edus.add(edu2);

        resume.setWorks(works);
        resume.setEdus(edus);

        System.out.println("before..edu.." +resume.getEdus().size());
        System.out.println("before..work.." +resume.getWorks().size());
        filter(resume);
        System.out.println("after edu..." + resume.getEdus().size());
        System.out.println("after work.." + resume.getWorks().size());
    }

    public static String filter(final User userFromResume) {
        try {
            if (userFromResume != null) {
                Class<?> clazz = userFromResume.getClass();
                for (Field f : clazz.getDeclaredFields()) {
                    f.setAccessible(true);
                    // System.out.println(".........." +f);
                    if (f.get(userFromResume) instanceof List<?>) {
                        List<?> list = (List<?>) f.get(userFromResume);
                        Iterator<?> iterator = list.iterator();
                        while (iterator.hasNext()) {
                            int count = 0;
                            Object object = iterator.next();
                            Field[] fields = object.getClass().getDeclaredFields();
                            for (Field e : fields) {
                                if (e.isAnnotationPresent(CheckValue.class)) {
                                     e.setAccessible(true);
                                    Class<?> clazzs = e.getAnnotation(CheckValue.class).value();
                                  //  for (Class<?> claz : clazzs) {
                                        if (clazzs.equals(String.class)&&Strings.isNullOrEmpty((String)e.get(object))){
                                            System.out.println(".........." +e.get(object)+" | " + e.getName());
                                            sb.append(object.getClass().getSimpleName()).append(":").append(FailReason.getReason(FailReason.valueOf(e.getName())));
                                            iterator.remove();
                                            break;
                                        }else if(clazzs == Integer.class&& (e.get(object) == null || (Integer)e.get(object) ==0)){
                                            System.out.println( object.getClass() + " | " + e.getName()+" |"+e.get(object));
                                            sb.append(object.getClass().getSimpleName()).append(":").append(FailReason.getReason(FailReason.valueOf(e.getName())));
                                            iterator.remove();
                                            break;
                                        }
                                        else if((clazzs==Date.class)&&e.get(object)==null){
                                            ++count;
                                            if(count>1){
                                                System.out.println("count.." +count+" | " +object.getClass() + " | " + e.getName());
                                                sb.append(object.getClass().getSimpleName()).append(":").append(FailReason.getReason(FailReason.valueOf(e.getName())));
                                                iterator.remove();
                                                break;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
        } catch (Exception e) {
            return sb.toString();
        }
        System.out.println(sb.toString());
        return sb.toString();
    }

    public static void filterThis(final User userFromResume) {
        try {
            if (userFromResume != null) {
                Class clazz = userFromResume.getClass();
                for (Field f : clazz.getDeclaredFields()) {
                    f.setAccessible(true);
                    if (f.get(userFromResume) instanceof List<?>) {
                        List<?> list = (List<?>) f.get(userFromResume);
                        Iterator<?> iterator = list.iterator();
                        while (iterator.hasNext()) {
                            Object object = iterator.next();
                            Field[] fields = object.getClass().getDeclaredFields();
                            for (Field field : fields) {
                                if (field.isAnnotationPresent(CheckValue.class)) {
                                    field.setAccessible(true);
                                    if (field.get(object) == null) {
                                        sb.append(object.getClass().getSimpleName()).append(":").append(FailReason.getReason(FailReason.valueOf(field.getName())))
                                                .append(";");
                                        iterator.remove();
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            return;
        }
        System.out.println(sb.toString());
    }
}

enum FailReason {
    userName("missiing user name"), password("missing password"), email("missing email"), name("missing name"), start("missing start time"), end("missing end time"), position(
            "missing position"), degree("missing degree");

    private String reason;

    private FailReason(String reason) {
        this.reason = reason;
    }

    public static String getReason(FailReason failReason) {
        return failReason.reason;
    }
}