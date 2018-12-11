package com.cong.springx.common.study.lambda;

/**
 *  lambda 表达式不能修改局部变量
 *  1. 成员变量和静态变量可以修改
 */
public class Lambda4 {

    public static int num1 = 2 ;

    private int num2 = 3;

    public static void main(String[] args) {
        new Lambda4().test();
    }

    /**
     *
     */
     public void test () {
         int n=3;
         Lambda4.Calculate calculate = (int p) -> {
             num1 = num1 + 1;
             num2 = num2 + 1;
             // n = n + 1;  编译直接会报错
             return n+p;
         };
         int sum = calculate.calculate(2);
         System.out.println(sum);
         System.out.println(num1);
         System.out.println(num2);
     }

    @FunctionalInterface
    public interface Calculate {
        int calculate(int value);
    }
}
