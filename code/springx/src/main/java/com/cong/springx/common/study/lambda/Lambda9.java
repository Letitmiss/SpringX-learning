package com.cong.springx.common.study.lambda;

import java.util.function.Supplier;

/**
 * Suppiler
 *    T get();
 */
public class Lambda9 {
    public static void main(String[] args) {
        test1();
        test2();
    }

    /**
     *  入参为空 返回 一个泛型
     */
    public static void test1() {
        Supplier<Dog> dogSupplier = Dog::new;
        //返回空构造的 dog 对象
        Dog dog = dogSupplier.get();
        System.out.println(dog.toString());
    }

    public static void test2() {
        Supplier<String> stringSupplier = () -> "Hello Lambda !!";
        String s = stringSupplier.get();
        System.out.println(s);
    }
}
