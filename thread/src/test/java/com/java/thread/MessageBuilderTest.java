package com.java.thread;

import com.java.thread.asy.MessageBuilder;
import org.junit.jupiter.api.Test;
 
 
public class MessageBuilderTest {

    @Test
    public void tes误为微物迁t2() {
        MessageBuilder obj = new MessageBuilder();
        System.out.println(obj.getCat());
        System.out.println(obj.getDog());
//        System.out.println(cat + dog);
    }

//    @Test
//    public void testGetMessage3() {
//        MessageBuilder obj = new MessageBuilder();
//        System.out.println(obj.getMessage("test"));
//        System.out.println(obj.getCat());
////        assertEquals("Hello test", obj.getMessage("test"));
//    }

    @Test
    public void testGetMessage1() {
        MessageBuilder obj = new MessageBuilder();
        System.out.println(obj.getMessage("test"));
//        System.out.println(obj.getCat());
//        assertEquals("Hello test", obj.getMessage("test"));
    }

//    @Test
//    public void testGetMessage2() {
//        MessageBuilder obj = new MessageBuilder();
//        assertEquals("empty!", obj.getMessage(""));
//    }
}