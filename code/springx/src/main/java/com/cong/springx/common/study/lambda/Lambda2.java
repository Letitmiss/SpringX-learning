package com.cong.springx.common.study.lambda;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;

/**
 * lambda的集合操作
 */
public class Lambda2 {
    public static List<String> strList= Arrays.asList("my","name","is","congcong");

    public static void main(String[] args) {
        //test1();
        //test2();
        //test3();
        test4();
        test5();
    }

    /**
     * 集合遍历 :
     *  之前 : 普通for循环和增强for循环
     *  现在 : java8 的 forEach方法  strList.forEach(Consumer)
     *
     */
     public static void test1 () {
         strList.forEach(new Consumer<String>() {
             @Override
             public void accept(String s) {
                 System.out.println(s);
             }
         });
     }
    /**
     * 推荐使用lambda表达式
     */
    public static void test2 () {
         strList.forEach(s -> System.out.println(s));
     }

    /**
     * lambda表达式的 :: 写法
     */
    public static void  test3 () {
        strList.forEach(System.out::println);
     }

    /**
     * 集合自定义的排序:
     * 1. 传统的匿名内部类实现
     */
    public static void test4 () {
        Collections.sort(strList, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.charAt(0) - o2.charAt(0);
            }
        });
        System.out.println(strList.toString());
     }

    /**
     * 集合自定义的排序:
     * 1. lambda表达式实现
     */
    public static void test5 () {
        Collections.sort(strList, (p1,p2) -> p1.charAt(0) - p2.charAt(0));
        System.out.println(strList.toString());
    }
}
