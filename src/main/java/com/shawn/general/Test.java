package com.shawn.general;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.base.Strings;


public class Test {
    public static void main(String[] args) throws Exception{
        //System.out.println(splitLongInt(654243441));
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
