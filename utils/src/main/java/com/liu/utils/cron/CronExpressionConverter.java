package com.liu.utils.cron;

import org.quartz.CronExpression;
import org.quartz.Trigger;
import org.quartz.impl.triggers.CronTriggerImpl;
import org.quartz.impl.triggers.SimpleTriggerImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 */
public class CronExpressionConverter {

    public static void main(String[] args) throws ParseException {
        List<Trigger> triggerList = getNextExecutionTime("24 * 0,1,2,3 1,2,3 * ? ", 10);
        triggerList.forEach(trigger -> {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
            String format = sdf.format(trigger.getNextFireTime());
            System.out.println(format);
        });
    }

    /**
     * 获取近几次的执行时间
     * @param cronExpressionString 表达式
     * @param count 近几次
     * @return
     * @throws ParseException
     */
    public static List<Trigger> getNextExecutionTime(String cronExpressionString, int count) throws ParseException {

        CronTriggerImpl trigger = new CronTriggerImpl();
        trigger.setCronExpression(new CronExpression(cronExpressionString));

        List<Trigger> triggerList = new ArrayList<>();
        Date now = new Date();
        for (int i = 0; i < count; i++) {
            Date nextExecutionTime = trigger.getFireTimeAfter(now);
            SimpleTriggerImpl st = new SimpleTriggerImpl();
            st.setNextFireTime(nextExecutionTime);
            st.setName("trigger" + (i + 1));
            triggerList.add(st);
            now = nextExecutionTime;
        }
        return triggerList;
    }

}