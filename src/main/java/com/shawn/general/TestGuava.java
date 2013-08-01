package com.shawn.general;

import java.util.List;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;

public class TestGuava {

    public static void main(String[] args) {
        List<String> strs = Lists.newArrayList("shawnCao@tianji.com","millll@oaid.COM","ad@ad.com","");
        List<String> after = Lists.transform(strs, new Function<String, String>() {
            public String apply(String input) {
                return input.toLowerCase();
            }
        });
        for (String t: after) {
            System.out.println(t.toString());
        }
    }
}
