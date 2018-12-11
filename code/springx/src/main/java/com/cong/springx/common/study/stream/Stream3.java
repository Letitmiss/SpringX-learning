package com.cong.springx.common.study.stream;

import javax.xml.transform.Source;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Streams 操作
 */
public class Stream3 {

    public static List<Person> personList = new ArrayList<>();

    public static List<Person> init() {
        Random random = new Random();
        for (int i = 0; i < 20 ; i++) {
            personList.add(new Person("person" + i, random.nextInt(50)));
        }
        return personList;
    }

    public static void main(String[] args) {
        init();
        //test1();
        //test5();
        //test6();
        //test7();
        test8();

    }

    /**
     * 进list转换为map
     */
    public static void test1() {
        Map<String, Person> collect = personList.stream()
                .filter(p -> p.getAge() > 20)
                .collect(Collectors.toMap(p -> p.getName(), p -> p));

        //map的遍历
        collect.forEach( (a,b) -> {
            System.out.println("key:"+a);
            System.out.println("value:" +b);
        } );
    }

    /**
     *  map 将一种流转化未另一种流
     */
    public static void  test2 () {
        //将Stream<Person>流转化为了 Stream<String>
        Stream<String> stringStream = personList.stream().map(p -> p.getName());
        stringStream.forEach(System.out::println);
    }

    /**
     * filter 过滤出年龄大于2
     */
    public static void test3() {
        personList.stream().filter(p -> p.getAge() > 20).forEach(System.out::println);
    }

    /**
     * flatmap拆解流
     */
    public static void test4 () {
        //将Stream<person>中的 年龄属性拆解出来作为 Stream<Integer> 流
        Stream<Integer> integerStream = personList.stream().flatMap(p -> Stream.of(p.getAge()));
    }

    /**
     * 排序
     */
    public static void test5 () {

        String[] arr1 = {"abc","a","bc","abcd"};
        //根据字母长度排序: 升序
        Arrays.stream(arr1).sorted(Comparator.comparing(String::length)).forEach(System.out::println);
        //自然排序 a b c 比较第一个字母
        Arrays.stream(arr1).sorted().forEach(System.out::println);
        //根据字母长度排序: 降序
        Arrays.stream(arr1).sorted(Comparator.comparing(String::length).reversed()).forEach(System.out::println);
        System.out.println("->");
        Arrays.stream(arr1).sorted((a,b) -> a.length() - b.length()).forEach(System.out:: println);
        System.out.println("多条件排序: ");
        Arrays.stream(arr1).sorted(Comparator.comparing(Stream3::com1).thenComparing(String::length)).forEach(System.out::println);
    }

    public static char com1 (String x) {
        return x.charAt(0);
    }

    /**
     * 提取流和限制流
     */
    public static void test6 () {

        //limit提起前n个数据
        Stream.iterate(1 , x -> x+2).limit(10).forEach(System.out::println);
        System.out.println("-------------");
        //skip跳过前n数据
        Stream.iterate(1 , x -> x+2).skip(1).limit(5).forEach(System.out::println);

    }

    /**
     * 合并流
     */
    public static void test7 () {
        String[] arr1 = new String[]{"a","b","c","d"};
        String[] arr2 = new String[]{"d","e","f","g"};
        String[] arr3 = new String[]{"i","j","k","l"};

        //将多个stream合并一个, 合并的stream的类型必须相同
        Stream<String> stream = Arrays.stream(arr1);
        Stream<String> stream1 = Arrays.stream(arr2);
        //distinct 表示去重 https://blog.csdn.net/haiyoung/article/details/80934467
        Stream.concat(stream,stream1).distinct().forEach(System.out::println);

    }

    /**
     * 聚合操作
     */
    public static void test8 () {

        String[] arr = new String[]{"b","ab","abc","abcd","abcde","abcdef"};
        //比较字符串长度返回
        Stream.of(arr).max(Comparator.comparing(String::length)).ifPresent(System.out::println);
        Optional<String> min = Stream.of(arr).min(Comparator.comparing(String::length));
        String s = min.get();
        System.out.println(s);

        //计算数量
        long count = Stream.of(arr).count();
        System.out.println("count :" + count);

        //查找第一个
        String nothing = Stream.of(arr).parallel().filter(x -> x.length() > 3).findFirst().orElse("nothing");
        System.out.println(nothing);
        String nothing1 = Stream.of(arr).parallel().filter(x -> x.length() > 5).findFirst().orElse("nothing");
        System.out.println(nothing1);

        //find any : 只要知找到第一个元素就返回,结束整个运算
        System.out.println("-------find any ----------");
        Optional<String> any = Stream.of(arr).filter(x -> x.length() > 3).findAny();
        any.ifPresent(System.out::println);

        //是否还有匹配元素
        boolean a = Stream.of(arr).anyMatch(x -> x.startsWith("a"));
        System.out.println(a);

        // Reduce 操作 https://blog.csdn.net/IO_Field/article/details/54971679
        Optional<String> reduce = Stream.of(arr).filter(x -> x.length() > 2).reduce((x, y) -> x + y);
        System.out.println(reduce.get());


    }




}
