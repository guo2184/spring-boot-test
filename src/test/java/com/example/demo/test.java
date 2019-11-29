package com.example.demo;


import com.google.gson.Gson;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author ：ghd
 * @date ：Created in 2019-11-27 09:55
 * @description：
 * @modified By：
 * @version: $
 */
public class test {

    @Test
    public void test1() {
        Map<String, String> map1 = new HashMap<>();
        map1.put("1", "a");
        map1.put("2", "a");
        Map<String, String> map2 = new HashMap<>();
        map2.put("1", "b");
        map2.put("2", "b");
        Map<String, String> collect = Stream.concat(map1.entrySet().stream(), map2.entrySet().stream())
                .collect(Collectors.toMap(
                Entry::getKey,
                Entry::getValue,
                (newval, oldval) -> oldval + ";" + newval
        ));
        System.out.println(new Gson().toJson(collect));
    }
}
