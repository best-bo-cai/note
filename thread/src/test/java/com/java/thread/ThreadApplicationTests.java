package com.java.thread;

import com.java.thread.asy.FeatureDemo;
import com.java.thread.asy.MessageBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

public class ThreadApplicationTests {

    @Test
    public void tes1tLoads() throws InterruptedException {
        String cat = FeatureDemo.getCat();
        String dog = FeatureDemo.getDog();
        System.out.println(cat + dog);
    }
}
