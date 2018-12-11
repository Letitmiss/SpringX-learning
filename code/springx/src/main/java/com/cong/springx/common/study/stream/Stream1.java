package com.cong.springx.common.study.stream;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * stream 提供一种 数据的集合的聚合操作
 * 1.stream不存储数据
 2.stream不改变源数据
 3.stream的延迟执行特性
 */
public class Stream1 {

    public static List<Person> personList = new ArrayList<>();

    public static void main(String[] args) {
        init();
        test1();


    }


    public static List<Person> init() {
        Random random = new Random();
        for (int i = 0; i < 20 ; i++) {
            personList.add(new Person("person" + i, random.nextInt(50)));
        }
        return personList;
    }

    /**
     * 过滤出:
     * 1. age大于30
     * 2. 按照名字顺序降序排列
     */
    public static void test1() {
        System.out.println(personList);
        List<Person> collect = personList.stream()
                .filter(p -> p.getAge() > 30)
                .sorted(Comparator.comparing(Person::getAge).reversed())
                .collect(Collectors.toList());
        System.out.println(collect);
    }
}
