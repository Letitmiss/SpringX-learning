package com.cong.springx.common.study.lambda;

/**
 * 自定义函数接口: 为了测试方便, 将接口定义在了,类里面,也可以单独定义
 */
public class Lambda3 {

    public static void main(String[] args) {
        test1();
    }

    /**
     * string to Integer : lambda 表达式
     */
    public static void test1 () {
        Convert<String,Integer> integerConvert = p -> Integer.valueOf(p);
        Integer convert = integerConvert.convert("123");
        System.out.println( "integerConvert :  "+ (convert + 3 ) );
    }

    /**
     * string to Integer : lambda 表达式 :: 写法
     * 1. 自定义一个转化类, 静态方法 , 通过 ::  调用
     *      public static Integer stringToInt(String from) {
             return Integer.valueOf(from);
             }
     */
    public static void test2 () {
        Convert<String,Integer> integerConvert = UtilTest::stringToInt;
        Integer convert = integerConvert.convert("123");
        System.out.println( "integerConvert :  "+ (convert + 3 ) );
    }

    /**
     *  定义一个函数式接口, 类型转换
     * @param <F>
     * @param <T>
     */
    @FunctionalInterface
    public interface Convert<F, T> {
        T convert(F from);
    }
}
