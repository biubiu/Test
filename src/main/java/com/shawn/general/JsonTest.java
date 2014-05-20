package com.shawn.general;


import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

import org.springframework.util.StringUtils;

import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonTest {

    private static final Gson gson = new GsonBuilder().enableComplexMapKeySerialization().create();

    public static void main(String[] args) throws IOException {
        Map<String, Map<String, String>> map = Maps.newConcurrentMap();
        map.put("/path1", Maps.newHashMap(new ImmutableMap.Builder<String, String>().put("host","www").put("log_type","error").build()));
        map.put("/path2", Maps.newHashMap(new ImmutableMap.Builder<String, String>().put("host","job").put("log_type","access").build()));
        map.put("/path3", Maps.newHashMap(new ImmutableMap.Builder<String, String>().put("log_type","default").build()));

        FileWriter writer = new FileWriter(new File("/home/shawncao/test.json"));
            writer.append(gson.toJson(map));
            writer.close();

            Map<String, Map<String, String>> gsss = gson.fromJson(new FileReader(new File("/home/shawncao/Desktop/logHeader.json")), Map.class);

            System.out.println(StringUtils.collectionToCommaDelimitedString(gsss.keySet()));
            for(String e: gsss.keySet()){
                System.out.println( e + ": "+ Joiner.on("&").withKeyValueSeparator("=").join(gsss.get(e)));
            }
    }
}
