package com.cong.springx.common.study.lambda;

import com.sun.org.apache.xerces.internal.dom.PSVIAttrNSImpl;

import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * java常用函数式接口 :  Function接口
 *
 *
 */
public class Lambda7 {

    public static void main(String[] args) {
        test1();
        test2();
        test3();
    }

    /**
     * apply方法测试 给一个 <T , R>  通过 T 返回 R
     */
    public static void test1 () {
        // Function<Integer,String> function = a -> String.valueOf(a);
        Function<Integer,String> function = String::valueOf;
        String apply = function.apply(123);
        System.out.println(apply);
    }

    /**
     * compose 方法 入参是function 返回也是function
     *  先执行入参function 的apply方法 ,返回值 -- 再执行调用者的apply方法
     *
     * andThen 方法 入参是function 返回也是function
     *  先执行调用者的apply方法, 返回值, 继续作为 入参 function的apply的调用
     *
     */
    public static void test2 () {

        Function<Integer,Integer> function1 = i -> i*2 ;
        Function<Integer,Integer> function2 = i -> i*i ;

        Integer num1 = function1.compose(function2).apply(5); //50
        //先执行function2  5*5  , 25 作为入参执行function1   25*2
        System.out.println(num1);
        Integer num2 = function1.andThen(function2).apply(5);
        //先执行function1 5*2 , 10 作为入参执行 function2 10*10
        System.out.println(num2);
    }

    /**
     *  BiFunction<T, U, R> {
     *     R apply(T t, U u);
     */
    public static void test3 () {
        BiFunction<Integer,Integer,String> biFunction = (a,b) -> String.valueOf(a+b);
        String apply = biFunction.apply(2, 3);
        System.out.println(apply);
    }
}

