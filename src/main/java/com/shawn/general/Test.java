package com.shawn.general;

public class Test {
    public static void main(String[] args) {
        String[] strs = getLocation("CN-11-北京,CN-12-上海");
        System.out.println("province: " + strs[0]);
        System.out.println("province: " + strs[1]);
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
