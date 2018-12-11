package com.cong.springx.common.study.lambda;

/**
 * @FunctionalInterface 注解表示未函数式接口, 只能有一个抽象方法 , 其余的 default
 */
public class lambda1 {

    public static void main(String[] args) {
        Running();
        JavaLambda();

    }

    /**
     * () -> System.out.println("I am running 2")
     * 参数实现接口的run方法;参数为空 (),
     * 方法执行代码只有一行System.out.println("I am running 2")
     */
    public static void JavaLambda () {
        new Thread(() -> System.out.println("I am running 2")).start();
    }

    /**
     * 匿名内部类, 实现方法 : 代码冗余
     */
    public static void Running() {
        Runnable runnable = new Runnable(){
            @Override
            public void run() {
                System.out.println("I am running");
            }
        };
        new Thread(runnable).start();
    }

}

