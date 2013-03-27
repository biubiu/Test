package com.shawn.classNInterface;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.common.collect.Sets;

public class TmpTst {

    private static final String EMAILREGEX = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static final String NAMEREGEX="";
    private static final String GENDERREGEX="";
            //"[1XLn] 9+K>C{3F2;D\6`SZ508v::WV#(R;8vasciiWV7{Kc0.58v::WV#)";

    public static void main(String[] args) {
            /*JobSearchServiceImpl search = new JobSearchServiceImpl();
            SearchCriterionVO searchCriterionVO = new SearchCriterionVO();
            searchCriterionVO.setKeyword("tianji");
            search.search(searchCriterionVO);*/
           /*     String s= "PM /n Account Manager";


                 String LUCENE_ESCAPE_CHARS ="[\\\\+\\-\\!\\(\\)\\:\\^\\]\\{\\}\\~\\*\\?]";
                 Pattern LUCENE_PATTERN = Pattern.compile(LUCENE_ESCAPE_CHARS);
                 String REPLACEMENT_STRING = "\\\\$0";
                 LUCENE_PATTERN.matcher(s);
                 System.out.println(s);*/
         //ids();
         //System.out.println(IsMatch("c.shangfei@gmail.com", EMAILREGEX));
         System.out.println(IsMatch("928_ooo@gmail.com.cn", EMAILREGEX));
        }

     private static boolean IsMatch(String string,String pattern) {
         try {
             Pattern patt = Pattern.compile(pattern);
             Matcher matcher = patt.matcher(string);
             return matcher.matches();
         } catch (RuntimeException e) {
             System.out.println("run error "+ e.toString());
             return false;
         }
     }

     public static void ids(){
         Integer[] intIds={2519,4221,4230,2902,3757,2470,3070,3301,3155,3114,3300,3232};
         HashSet<Integer> ids = Sets.newHashSet();
         Random random = new Random();
         while(true){
             ids.add(Math.abs(random.nextInt()%(intIds.length-1)));
             if(ids.size()==4){
                 break;
             }
         }
         Iterator<Integer> its = ids.iterator();
         while(its.hasNext()){
             System.out.println(its.next());
         }
         Integer[] res = (Integer[])ids.toArray();
     }
}


