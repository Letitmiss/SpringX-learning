package com.cong.springx.common.study.lambda;

import java.util.function.Predicate;

public class Lambda5 {

    public static void main(String[] args) {

        test1();
        test2();
        test3();

    }

    /**
     * 创建接口: 传统实现 :  匿名内部类实现
     */
    public static void test1 () {

        AnimalFactory dogFactory= new AnimalFactory() {
            @Override
            public Animal create(String name, int age) {
                return new Dog(name, age);
            }
        };
        Animal name = dogFactory.create("name", 22);
        name.behavior();
    }

    /**
     *  函数式接口 : lambda表达式实现 ->
     */
    public static void test2 () {

        AnimalFactory<Dog> dogAnimalFactory= (a,b) -> new Dog(a,b);
        Animal dog = dogAnimalFactory.create("dog", 18);
        dog.behavior();
    }

    /**
     *  函数式接口 : lambda表达式实现 :: 调用构造
     */
    public static void test3 () {

        AnimalFactory<Dog> dogAnimalFactory= Dog::new;
        Animal dog = dogAnimalFactory.create("dog", 18);
        dog.behavior();
    }
}
