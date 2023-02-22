package com.java.thread.asy;

public class MessageBuilder {
    public String getMessage(String name) {
        StringBuilder result = new StringBuilder();
//        if (name == null || name.trim().length() == 0) {
//            result.append("empty!");
//        } else {
//            result.append("Hello " + name);
//        }
        result.append("Hello " + name);
        return result.toString();
    }

    public String getCat() {
        System.out.println("猫");
        return "Tom";
    }

    public String getDog() {
        System.out.println("鼠");
        return "Jerry";
    }
}