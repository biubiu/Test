package com.shawn.enumNAnnotation;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * User: Shawn cao
 * Date: 13-3-24
 * Time: PM6:51
 */
public class ReflectionListCheckTest {

    private static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException {

        // set model from rest
        User resume = new User();
       // resume.setEmail("fzxwolf@sina.com");
        resume.setUserName("fzx");
        resume.setPassword("123456");

        User.Work work1 = new User.Work();
        work1.setName("work1");
        work1.setPosition("position1");
        work1.setStart(13000000l);
        work1.setEnd(12000000l);

        User.Work work2 = new User.Work();
        work2.setName("work2");
        work2.setPosition("position2");
        work2.setStart(13000000l);

        List<User.Work> works = new ArrayList<User.Work>();
        works.add(work1);
        works.add(work2);

        User.Edu edu1 = new User.Edu();
        edu1.setName("edu1");
        edu1.setStart(1300000l);
        edu1.setEnd(1200000l);

        User.Edu edu2 = new User.Edu();
        edu2.setName("edu2");
        edu2.setDegree("degree2");
        edu2.setStart(1300000l);
        edu2.setEnd(120000l);

        List<User.Edu> edus = new ArrayList<User.Edu>();
        edus.add(edu1);
        edus.add(edu2);

        resume.setWorks(works);
        resume.setEdus(edus);

        System.out.println("before..edu.." +resume.getEdus().size());
        System.out.println("before..work.." +resume.getWorks().size());
        filterThis(resume);
        System.out.println(resume.getEdus().size());
        System.out.println(resume.getWorks().size());
    }

    public static void filterThis(final User userFromResume){
        try {
            if(userFromResume!=null){
                Class clazz = userFromResume.getClass();
                for (Field f : clazz.getDeclaredFields()) {
                    f.setAccessible(true);
                    if(f.get(userFromResume) instanceof List<?>){
                        List<?> list = (List<?>)f.get(userFromResume);
                        Iterator<?> iterator = list.iterator();
                        while(iterator.hasNext()){
                            Object object = iterator.next();
                            Field[] fields = object.getClass().getDeclaredFields();
                            for(Field field : fields){
                                if(field.isAnnotationPresent(CheckValue.class)){
                                    field.setAccessible(true);
                                    if(field.get(object)==null){
                                        sb.append(object.getClass().getSimpleName()).append(":").
                                           append(FailReason.getReason(FailReason.valueOf(field.getName()))).
                                           append(";");
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
enum FailReason{
    userName("missiing user name"),
    password("missing password"),
    email("missing email"),
    name("missing name"),
    start("missing start time"),
    end("missing end time"),
    position("missing position"),
    degree("missing degree");

    private String reason;
    private FailReason(String reason){
        this.reason = reason;
    }

    public static String getReason(FailReason failReason){
        return failReason.reason;
    }
}