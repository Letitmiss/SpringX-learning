package com.cong.springx.common.study.stream;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class Stream4 {

    public static void main(String[] args) {
        //test1();
        //test2();
        test3();
    }

    /**
     * optional类型 : 聚合操作 会返回一个安全非空类型
     *  isPresent() 判断返回值是否为空
     *  ifPresent(Consumer<? super T> consumer) 消费返回值
     *  get() 获取值
     *
     *
     */
    public static void test1 () {
        List<String> list = Arrays.asList("user1", "user2");
        Optional<String> optional = Optional.of("and with you");
        optional.ifPresent(list::add);
        list.forEach(System.out::println);
    }

    public static void test2 () {
        Integer[] arr = new Integer[]{4,5,6,7,8,9};
        Integer result = Stream.of(arr).filter(x->x>9).max(Comparator.naturalOrder()).orElse(-1);
        System.out.println(result);
        Integer result1 = Stream.of(arr).filter(x->x>9).max(Comparator.naturalOrder()).orElseGet(()->-1);
        System.out.println(result1);
        Integer result2 = Stream.of(arr).filter(x->x>8).max(Comparator.naturalOrder()).orElseThrow(RuntimeException::new);
        System.out.println(result2);
    }

    /**
     * optional的创建 : map 的转换
     */
    public static void test3 () {

        Optional<Person> cong = Optional.of(new Person("cong", 18));
        Optional<String> optional = cong.map(Person::getName);
        System.out.println(optional.get());
    }







}
