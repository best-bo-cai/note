package com.liu.question.inherit.q1;

import cn.hutool.core.lang.Console;

/**
 * 我有一个类A和一个类B，类B继承了A。当别人给我的B类对象时，我会从中取出A单独去处理，这个A我怎么去获取?
 */
// 先定义父类 A
public class A {
    private String name;
    private String gender;

    public A(String name, String gender) {
        this.name = name;
        this.gender = gender;
    }

    public String getGender() {
        return gender;
    }

    public String getName() {
        return name;
    }

    public static void main(String[] args) {
        B b = new B("Tom", 20, "男");  // 创廯同时定位局郴�和公共局郴�;  // 创建一个 B 类对象
        A a = b;  // 将 B 类对象看作 A 类对象
        System.out.println(a.getName() + a.getGender());  // 输出 "Tom"
        System.out.println(b.getAge());  // 输出 "20"
    }
}



