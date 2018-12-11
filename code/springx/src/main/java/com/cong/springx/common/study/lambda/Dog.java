package com.cong.springx.common.study.lambda;

import com.cong.springx.common.study.lambda.Animal;

public class Dog extends Animal {

    public Dog(String name, int age) {
        super(name, age);
    }

    public Dog() {
    }

    @Override
    public void behavior() {
        System.out.println("run");
    }
}
