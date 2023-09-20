package com.liu.question.inherit.q1;

import com.liu.question.inherit.q1.A;

// 定义子类 B，继承自 A
public class B extends A {
    private int age;

    public B(String name, int age, String gender) {
        super(name, gender);  // 调用父类的构造函数
        this.age = age;
    }

    public int getAge() {
        return age;
    }
}
