package com.cong.springx.common.study.lambda;

import com.sun.org.apache.xerces.internal.dom.PSVIAttrNSImpl;

import javax.xml.bind.SchemaOutputResolver;
import java.util.function.Consumer;

/**
 * void accept(T t);
 */
public class Lambda8 {
    public static void main(String[] args) {
        test1();
        test2();
    }

    /**
     *  有输入  无输出 ;
     *  修改属性值
     */
    public static void  test1 () {
        Consumer<Dog> consumer = d ->d.setName("dog2");
        Dog dog = new Dog("dog1", 18);
        consumer.accept(dog);
        System.out.println(dog.getName()); //dog2
    }

    /**
     * 链式编程 andThen方法
     */
    public static void  test2 () {
        Consumer<Dog> consumer1 = d ->d.setName("dog2");
        Consumer<Dog> consumer2 = d ->d.setAge(20);

        Dog dog = new Dog("dog1", 18);
        consumer1.andThen(consumer2).accept(dog);
        System.out.println(dog.getName()+":"+ dog.getAge()); //dog2:20
    }

}


