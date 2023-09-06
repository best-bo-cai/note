package com.liu.utils.date;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class NowDateTimeCalculate {
    public static void main(String[] args) {
        // 获取当前时间
        LocalDateTime now = LocalDateTime.now();
        
        // 当前时间减去一小时
        LocalDateTime oneHourAgo = now.minusHours(2);
        
        // 当前时间减去一天
        LocalDateTime oneDayAgo = now.minusDays(2);
        
        // 当前时间减去一周
        LocalDateTime oneWeekAgo = now.minusWeeks(1);
        
        // 当前时间减去一个月
        LocalDateTime oneMonthAgo = now.minusMonths(1);
        
        // 当前时间减去一年
        LocalDateTime oneYearAgo = now.minusYears(1);
        
        // 打印结果
        System.out.println("当前时间：" + now.toInstant(ZoneOffset.UTC).toEpochMilli());
        System.out.println("一小时前：" + oneHourAgo);
        System.out.println("一天前：" + oneDayAgo.toEpochSecond(ZoneOffset.UTC));
        System.out.println("一周前：" + oneWeekAgo);
        System.out.println("一个月前：" + oneMonthAgo);
        System.out.println("一年前：" + oneYearAgo);
    }
}
