package com.shawn.guava;

import com.google.common.cache.*;
import com.google.common.collect.ImmutableMap;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;

/**
 * User: Shawn cao
 * Date: 14-6-9
 * Time: AM10:05
 */
public class CacheTest {



    @Test
    public void testCacheByCacheLoader() throws Exception{
        LoadingCache<String,String> cache = CacheBuilder.newBuilder().maximumSize(10).build(new CacheLoader<String, String>() {
            @Override
            public String load(String s) throws Exception {
                return maps.get(s);
            }
        });
        assertTrue(cache.get("a").equals("aaa"));
        assertTrue(cache.size() == 1);
        assertTrue(cache.get("b").equals("bbb"));
        assertTrue(cache.size() == 2);
    }

    @Test
    public void testCacheByCallable() throws Exception{
        Cache<String,String> cache = CacheBuilder.newBuilder().build();
        String result = cache.get("Herry",new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "Hello Herry !";
            }
        });
        assertTrue(result.equals("Hello Herry !"));

        result = cache.get("Jerry",new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "Goodbye Herry, welcome Jerry !";
            }
        });
        assertTrue(result.equals("Goodbye Herry, welcome Jerry !"));
    }


    static ImmutableMap<String,String> maps = ImmutableMap.of("a","aaa","b","bbb");


    private <K , V> LoadingCache<K , V> cached(CacheLoader<K , V> cacheLoader) {
        LoadingCache<K , V> cache = CacheBuilder
                .newBuilder()
                .maximumSize(2)
                .weakKeys()
                .softValues()
                .refreshAfterWrite(120, TimeUnit.SECONDS)
                .expireAfterWrite(10, TimeUnit.MINUTES)
                .removalListener(new RemovalListener<K, V>(){
                    @Override
                    public void onRemoval(RemovalNotification<K, V> rn) {
                        System.out.println(rn.getKey()+" is removed");

                    }})
                .build(cacheLoader);
        return cache;
    }

    private LoadingCache<String,String> commonCache(final String key) {
        return cached(new CacheLoader<String, String>() {
            @Override
            public String load(String s) throws Exception {
                return "Hello "+s;
            }
        });
    }

    @Test
    public void testLoadingCache() throws ExecutionException {
        LoadingCache<String,String> commonCache = commonCache("herry");
        assertTrue("Hello herry".equals(commonCache.get("herry")));
        assertTrue("Hello Toby".equals(commonCache.get("Toby")));
        assertTrue("Hello Howie".equals(commonCache.get("Howie")));
        commonCache.invalidate("herry");
        commonCache.invalidateAll();
    }
}
