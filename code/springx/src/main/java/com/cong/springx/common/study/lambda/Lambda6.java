package com.cong.springx.common.study.lambda;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * java内置函数式接口 : Predicate
 */
public class Lambda6 {

    public static List<Integer> numList = Arrays.asList(1,2,3,4,5,6,7,8,9,10);

    public static void main(String[] args) {
        test1();
        test2();
        test3();
        test4();
        test5();
    }

    /**
     * Predicate : 做逻辑判断
     *  结合stream的api使用更好
     */
    public static void test1 () {

        Predicate<String> predicate = s -> s.length() > 0 ;
        boolean test = predicate.test("213");
        System.out.println(test);
    }

    /**
     * 定义抽象方法 : 根据传入的predicate 的判断结果, 将true的结果返回 ;
     *  predicate是一个函数式接口 : 在使用的时候可以通过lambda表达式:  实现判断的规则
     * @param list
     * @param predicate
     * @return
     */
    public static List<Integer> customFilter(List<Integer> list,Predicate<Integer> predicate) {
        return list.stream().filter(predicate).collect(Collectors.toList());
    }

    /**
     * 过滤出大于5的
     */
    public static void test2 () {
        List<Integer> integers = customFilter(numList, i -> i > 5);
        integers.forEach(System.out::println);
    }

    /**
     * 过滤小于8的
     */
    public static void test3 () {
        List<Integer> integers = customFilter(numList, i -> i < 8);
        integers.forEach(System.out::println);
    }

    /**
     *  返回所有
     */
    public static void test4 () {
        List<Integer> integers = customFilter(numList, i -> true);
        integers.forEach(System.out::println);
    }

    /**
     *  Predicate :  and or 和 negate 三个default方法
     *          多条件判断 且 或 反
     */
    public static void test5 () {

        Predicate<String> predicate = s -> s.length() > 0 ;
        Predicate<String> andPredicate = predicate.and(s -> s.length() <= 3);

        boolean test = andPredicate.test("213"); //true
        System.out.println(test);
        boolean test1 = andPredicate.test("1234"); //false
        System.out.println(test1);
    }

}
