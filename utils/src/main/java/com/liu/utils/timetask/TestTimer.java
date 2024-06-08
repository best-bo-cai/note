package com.liu.utils.timetask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
public class TestTimer {
    private static final Logger log = LoggerFactory.getLogger(TestTimer.class);


    public static void main(String[] args) {

        timeTasks();
        scheduledExecutorServiceTasks();
    }

    private static void timeTasks() {
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                log.info("TimerTask  run: {}", new Date());
            }
        };

        Timer timer = new Timer();
        //安排指定的任务在指定的时间开始进行重复的固定延迟执行。这里是每3秒执行一次
        timer.schedule(timerTask, 10, 3000);
    }

    private static void scheduledExecutorServiceTasks() {

        ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(1,
                new BasicThreadFactory.Builder().namingPattern("example-schedule-pool-%d").daemon(true).build());
        executorService.scheduleAtFixedRate(() -> log.info("scheduledExecutorServiceTasks  run: {}", new Date()),
                0, 3, TimeUnit.SECONDS);
    }

//    @EnableScheduling
    @Scheduled(cron = "0/3 * * ? * ? ")
    public void scheduledTask() {
        log.info("scheduledTask  run: {}", new Date());
    }
}