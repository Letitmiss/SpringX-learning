package com.cong.springx.common.study.lambda;

public class Bird extends Animal {

    public Bird(String name, int age) {
        super(name, age);
    }

    @Override
    public void behavior() {
        System.out.println("fly");
    }
}
