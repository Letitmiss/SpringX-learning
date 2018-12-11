package com.cong.springx.common.study.stream;

import java.security.PublicKey;
import java.util.HashMap;
import java.util.Map;

public class Test {
    public static void main(String[] args) {
        Map<String,String> map = new HashMap<>();
        map.put("1","aaaa");
        map.put("2","bbbb");
        map.put("1","cccc");

        System.out.println(map.toString());

    }
}
