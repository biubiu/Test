package com.shawn.general;



import java.util.List;

import org.springframework.util.StringUtils;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;


public class Test {
    final private static char seperator = ',';
    final private static int ITERS =  100_000;
    public static void main(String[] args) throws Exception{

        //System.out.println(splitLongInt(654243441));

        //long start = System.currentTimeMillis();
        /*for(int i=0;i<1000000;i++){
            List<String> res = splitPrimitive("1,5,4,9,12,23,34");
        }*/

        /*String res = "";
        for ( int i = 0; i < ITERS; ++i )
        {
            final String s = Integer.toString( i );
            res = res.concat( s ); //second option: res += s;
        }
        System.out.println("end : " + (System.currentTimeMillis()-start));*/



        /*long start2 = System.currentTimeMillis();
        for(int i=0;i<1000000;i++){
            Iterable<String> res = split2("1,5,4,9,12,23,34");
        }
        StringBuilder res = new StringBuilder();
        for ( int i = 0; i < ITERS; ++i )
        {
            final String s = Integer.toString( i );
            res.append( s );
        }
        System.out.println("end : " + (System.currentTimeMillis()-start2) + " | "+ res.length());*/
        //System.out.println(StringUtils.collectionToDelimitedString(res, "--"));

        String string = "";
        System.out.println(StringUtils.collectionToCommaDelimitedString(Lists.newArrayList(string.split(","))));
    }

    private static Iterable<String> split2(final String commaStr){
        Iterable<String> res =  Splitter.on(",").split(commaStr);
        return res;
     }
    private static  List<String> splitPrimitive(final String commaStr){
        final List<String> res = Lists.newArrayListWithCapacity(10);
        int pos,prev = 0;
        while((pos = commaStr.indexOf(seperator,prev))!=-1){
            res.add(commaStr.substring(prev,pos));
            prev = pos+1;//reset to next char
        }
        return res;
    }

    private static String splitLongInt(Integer longInt){
        String origin = String.valueOf(longInt);
        if(origin.length() < 9){
            origin = Strings.padStart(origin, 9, '0');
        }
        return Joiner.on("/").appendTo(new StringBuilder(),Splitter.fixedLength(3).split(origin)).toString();
    }

    private static void splitLongString(){
         String string="工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述工作概述";
         System.out.println(string.length());
         if(string.length() > 40){
             String[] strs = new String[string.length()/40 + 1];

             for (int i = 0; i <= string.length()/40; i++) {
                 int from = i*40;

                 int to = i==string.length()/40 ? string.length() : (i+1)*40;
                 System.out.println("from " + from + " to : " + to);
                 strs[i] = string.substring(from,to);
                 System.out.println("in: " + strs[i]);
             }
             for(int i =0 ; i<strs.length;i++){
                 System.out.println("out: " + strs[i]);
             }
         }

    }
    public static String[] getLocation(String location) {
        String[] strs = new String[2];
        String[] locs = location.split(",");
        String province = "";
        String city = "";
        for (int i = 0; i < locs.length; i++) {
            String[] tmp = locs[i].split("-");
            if(tmp!=null && tmp.length>2){
                province += tmp[1] + ",";
                city += tmp[2] + ",";
            }
        }
        strs[0] = province;
        strs[1] = city;
        return strs;
    }
}
