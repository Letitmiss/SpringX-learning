package com.cong.springx.common.study.stream;

import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * 创建 stream
 */
public class Stream2 {

    public static void main(String[] args) {
        //test1();
        //test2();
        //test3();
        test4();

    }

    public static void test1 () {
        //数组
        String[] strArray = new String[]{"123","234","456"};
        Stream<String> stream = Arrays.stream(strArray);
        int[] intArray=new int[] {1,2,4};
        IntStream stream1 = Arrays.stream(intArray);

        Person[] personArray = new Person[]{new Person("aa",18),new Person("bb",19)};
        Stream<Person> stream2 = Arrays.stream(personArray);

        Stream<Integer> integerStream = Stream.of(1, 3, 5);
        Stream<Person> personArray1 = Stream.of(personArray);

        Stream<int[]> intArray1 = Stream.of(intArray, intArray);
    }

    public static void test2 () {
        List<String> strings = Arrays.asList("123", "456", "789");
        //创建流同归 list
        Stream<String> stream = strings.stream();
        //创建并行流
        Stream<String> stringStream = strings.parallelStream();
    }

    /**
     * 创建无限流
     */
    public static void test3 () {
        //创建随机数流, 100个
        Stream.generate(() -> "number"+new Random().nextInt()).limit(100).forEach(System.out::println);
        Stream.generate(() -> new Person("name",10)).limit(10).forEach(System.out::println);
    }

    public static void test4 () {
       Stream.iterate(0,x -> x+1).limit(10).forEach(System.out::println);
       Stream.iterate(0,x -> x).limit(10).forEach(System.out::println);
    }
}
