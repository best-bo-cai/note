package com.java.thread.asy;

import lombok.extern.slf4j.Slf4j;

@Slf4j
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
        log.info("猫");
        return "Tom";
    }

    public String getDog() {
        log.info("鼠");
        return "Jerry";
    }
}