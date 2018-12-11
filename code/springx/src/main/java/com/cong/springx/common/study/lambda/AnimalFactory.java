package com.cong.springx.common.study.lambda;

@FunctionalInterface
public interface AnimalFactory<T extends Animal> {
    T create(String name,int age);

}
