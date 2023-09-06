package com.liu.utils.date;

import java.time.*;

public class TimestampUtils {

    // 获取当前时间戳（毫秒）
    public static long getCurrentTimestamp() {
        return System.currentTimeMillis();
    }

    // 获取当前时间戳（秒）
    public static long getCurrentTimestampInSeconds() {
        return System.currentTimeMillis() / 1000;
    }

    // 当前小时的0分钟的时间戳（毫秒）
    public static long getHourTimestamp() {
        LocalDateTime today = LocalDateTime.now();
        LocalDateTime currentHour = today.withMinute(0).withSecond(0);
        return currentHour.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    // 当前小时的0分钟的时间戳（秒）
    public static long getHourTimestampInSeconds() {
        LocalDateTime today = LocalDateTime.now();
        LocalDateTime hourStart = today.withMinute(0).withSecond(0);
        return hourStart.atZone(ZoneId.systemDefault()).toInstant().getEpochSecond();
    }

    // 获取今天0点的时间戳（毫秒）
    public static long getTodayTimestamp() {
        LocalDate today = LocalDate.now();
        LocalDateTime todayStart = LocalDateTime.of(today, LocalTime.MIDNIGHT);
        return todayStart.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    // 获取今天0点的时间戳（秒）
    public static long getTodayTimestampInSeconds() {
        LocalDate today = LocalDate.now();
        LocalDateTime todayStart = LocalDateTime.of(today, LocalTime.MIDNIGHT);
        return todayStart.atZone(ZoneId.systemDefault()).toInstant().getEpochSecond();
    }

    // 获取本周一0点的时间戳（毫秒）
    public static long getCurrentWeekMondayTimestamp() {
        LocalDate today = LocalDate.now();
        LocalDate monday = today.with(DayOfWeek.MONDAY);
        LocalDateTime mondayStart = LocalDateTime.of(monday, LocalTime.MIDNIGHT);
        return mondayStart.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    // 获取本周一0点的时间戳（秒）
    public static long getCurrentWeekMondayTimestampInSeconds() {
        LocalDate today = LocalDate.now();
        LocalDate monday = today.with(DayOfWeek.MONDAY);
        LocalDateTime mondayStart = LocalDateTime.of(monday, LocalTime.MIDNIGHT);
        return mondayStart.atZone(ZoneId.systemDefault()).toInstant().getEpochSecond();
    }

    // 获取本月1号0点的时间戳（毫秒）
    public static long getCurrentMonthFirstDayTimestamp() {
        LocalDate today = LocalDate.now();
        LocalDate firstDay = today.withDayOfMonth(1);
        LocalDateTime firstDayStart = LocalDateTime.of(firstDay, LocalTime.MIDNIGHT);
        return firstDayStart.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    // 获取本月1号0点的时间戳（秒）
    public static long getCurrentMonthFirstDayTimestampInSeconds() {
        LocalDate today = LocalDate.now();
        LocalDate firstDay = today.withDayOfMonth(1);
        LocalDateTime firstDayStart = LocalDateTime.of(firstDay, LocalTime.MIDNIGHT);
        return firstDayStart.atZone(ZoneId.systemDefault()).toInstant().getEpochSecond();
    }

    // 获取本年1月1号0点的时间戳（毫秒）
    public static long getCurrentYearFirstDayTimestamp() {
        LocalDate today = LocalDate.now();
        LocalDate firstDay = today.withMonth(1).withDayOfMonth(1);
        LocalDateTime firstDayStart = LocalDateTime.of(firstDay, LocalTime.MIDNIGHT);
        return firstDayStart.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    // 获取本年1月1号0点的时间戳（秒）
    public static long getCurrentYearFirstDayTimestampInSeconds() {
        LocalDate today = LocalDate.now();
        LocalDate firstDay = today.withMonth(1).withDayOfMonth(1);
        LocalDateTime firstDayStart = LocalDateTime.of(firstDay, LocalTime.MIDNIGHT);
        return firstDayStart.atZone(ZoneId.systemDefault()).toInstant().getEpochSecond();
    }

    public static void main(String[] args) {
        System.out.println("当前时间戳（毫秒）：" + getCurrentTimestamp());
        System.out.println("当前时间戳（秒）：" + getCurrentTimestampInSeconds());
        System.out.println("当前小时的0分钟的时间戳（毫秒）：" + getHourTimestamp());
        System.out.println("当前小时的0分钟的时间戳（秒）：" + getHourTimestampInSeconds());
        System.out.println("今天0点的时间戳（毫秒）：" + getTodayTimestamp());
        System.out.println("今天0点的时间戳（秒）：" + getTodayTimestampInSeconds());
        System.out.println("本周一0点的时间戳（毫秒）：" + getCurrentWeekMondayTimestamp());
        System.out.println("本周一0点的时间戳（秒）：" + getCurrentWeekMondayTimestampInSeconds());
        System.out.println("本月1号0点的时间戳（毫秒）：" + getCurrentMonthFirstDayTimestamp());
        System.out.println("本月1号0点的时间戳（秒）：" + getCurrentMonthFirstDayTimestampInSeconds());
        System.out.println("本年1月1号0点的时间戳（毫秒）：" + getCurrentYearFirstDayTimestamp());
        System.out.println("本年1月1号0点的时间戳（秒）：" + getCurrentYearFirstDayTimestampInSeconds());
    }
}