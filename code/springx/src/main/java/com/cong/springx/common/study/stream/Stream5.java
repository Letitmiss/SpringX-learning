package com.cong.springx.common.study.stream;

import com.sun.corba.se.impl.orb.PropertyOnlyDataCollector;

import java.lang.reflect.Array;
import java.rmi.StubNotFoundException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 收集结果
 */
public class Stream5 {


     public static Person[] persons;
    public static void main(String[] args) {
        init();
       // test1();
        //test2();
        //test4();
        //test5();
        //test6();
        //test7();
        //test8();
        test9();
    }

    public static void init () {
        persons = new Person[100];
        for (int i=0;i<30;i++){
            Person student = new Person("user",i);
            persons[i] = student;
        }
        for (int i=30;i<60;i++){
            Person student = new Person("user"+i,i);
            persons[i] = student;
        }
        for (int i=60;i<100;i++){
            Person student = new Person("user"+i,i);
            persons[i] = student;
        }
    }

    public static void test1() {
        List<Person> collect = Arrays.stream(persons).collect(Collectors.toList());
        collect.forEach((x) -> System.out.println(x));

        Set<Person> collect1 = Arrays.stream(persons).collect(Collectors.toSet());
        collect1.forEach((x) -> System.out.println(x));

        Map<String, Integer> collect2 = Arrays.stream(persons).collect(Collectors.toMap(Person::getName, Person::getAge));
        collect2.forEach((x,y) -> System.out.println(x+"->"+y));

    }
    /*
      转化未数组
     */
    public static void  test2() {
        Person[] people = Arrays.stream(persons).toArray(Person[]::new);
    }

    /**
     * 统计
     */
    public static void test3 () {
        IntSummaryStatistics collect = Arrays.stream(persons).collect(Collectors.summarizingInt(Person::getAge));
        double average = collect.getAverage();
        long sum = collect.getSum();
        int max = collect.getMax();
    }

    /**
     * 分组统计
     */
    public static void test4() {
        Map<String, List<Person>> collect = Arrays.stream(persons).collect(Collectors.groupingBy(Person::getName));
        collect.forEach((a,b) -> System.out.println(a+ "->" + b));
    }

    public static void test5() {

        Map<Boolean, List<Person>> collect = Arrays.stream(persons).collect(Collectors.partitioningBy(x -> x.getAge() > 50));

        collect.forEach((a,b) -> System.out.println(a+ "->" + b));
    }

    public static void test6() {

        Map<String, Set<Person>> collect = Arrays.stream(persons).collect(Collectors.groupingBy(Person::getName, Collectors.toSet()));
        collect.forEach((a,b) -> System.out.println(a+ "->" + b));
    }

    public static void test7() {
        Map<String, Long> collect = Arrays.stream(persons).collect(Collectors.groupingBy(Person::getName, Collectors.counting()));
        collect.forEach((a,b) -> System.out.println(a+ "->" + b));
        System.out.println("---------summingInt-----");
        Map<String, Integer> collect1 = Arrays.stream(persons).collect(Collectors.groupingBy(Person::getName, Collectors.summingInt(Person::getAge)));
        collect1.forEach((a,b) -> System.out.println( a +"->"+ b));
        System.out.println("------maxBy----------");
        Map<String, Optional<Person>> collect2 = Arrays.stream(persons).collect(Collectors.groupingBy(Person::getName, Collectors.maxBy(Comparator.comparing(Person::getAge))));
        collect2.forEach((a,b) -> System.out.println( a +"->"+ b));
        System.out.println("-------mapping---------");
        //按照名字分组, 将年龄作为 set集合返回,
        Map<String, Set<Integer>> collect3 = Arrays.stream(persons).collect(Collectors.groupingBy(Person::getName, Collectors.mapping(Person::getAge, Collectors.toSet())));
        collect3.forEach((a,b) -> System.out.println( a +"->"+ b));

       // Arrays.stream(persons).reduce();
    }



    public static void test8 () {

        Stream<Integer> limit = Stream.iterate(1, x -> x + 1).limit(10).parallel();
        limit.peek(Stream5::peek1).filter( x -> x > 5 )
                .peek(Stream5::peek2).filter( x -> x < 8)
                .peek(Stream5::peek3)
                .forEach(System.out::println);

    }

    public static void peek1(int x) {
        System.out.println(Thread.currentThread().getName() + ":->peek1->" + x);
    }

    public static void peek2(int x) {
        System.out.println(Thread.currentThread().getName() + ":->peek2->" + x);
    }

    public static void peek3(int x) {
        System.out.println(Thread.currentThread().getName() + ":->final result->" + x);
    }


    public static void test9 () {
        Random random = new Random();
        List<Integer> list = Stream.generate(() -> random.nextInt(100)).limit(100000000).collect(Collectors.toList());
        System.out.println("count---------");
        long begin1 = System.currentTimeMillis();

        list.stream().filter(x->(x > 10)).filter(x->x<80).count();
        long end1 = System.currentTimeMillis();
        System.out.println(end1-begin1);

        list.stream().parallel().filter(x->(x > 10)).filter(x->x<80).count();

        long end2 = System.currentTimeMillis();
        System.out.println(end2-end1);

        System.out.println("sorted distinct 全局相关");
        long begin1_ = System.currentTimeMillis();
        list.stream().filter(x->(x > 10)).filter(x->x<80).distinct().sorted().count();
        long end1_ = System.currentTimeMillis();
        System.out.println(end1_-begin1_);
        list.stream().parallel().filter(x->(x > 10)).filter(x->x<80).distinct().sorted().count();
        long end2_ = System.currentTimeMillis();
        System.out.println(end2_-end1_);


    }






}
