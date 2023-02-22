package com.java.thread;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class JsonTest {

    @Test
    public void test() {
        List<List<Integer>> integers = new ArrayList<>();

        List<Integer> integers1 = List.of(33, 34, 35, 36);
        List<Integer> integers2 = new ArrayList<>();
        List<Integer> integers3 = List.of(7, 8, 9);
        integers.add(integers1);
        integers.add(integers2);
        integers.add(integers3);

        System.out.println(integers);

        String s = JSON.toJSONString(integers);
        System.out.println(s);

        List<List<Integer>> list = JSON.parseObject(s, new TypeReference<List<List<Integer>>>() {
        });

        System.out.println(list);

        System.out.println(list.get(0));
        Boolean aBoolean = checkNowTimeIsInTimeSlot(list);
        System.out.println(aBoolean);
    }

    //[[1, 2, 3], [], [7, 8, 9],[],[],[],[]]

    /**
     * 数组长度范围[0,6]
     * 数组内数值范围 [0,47]
     *
     * @return
     */
    public static Boolean checkNowTimeIsInTimeSlot(List<List<Integer>> timePeriod) {
        //获取当前时间
        Date nowTime = new Date();
        //转化为指定格式
        SimpleDateFormat sdfHour = new SimpleDateFormat("HH");
        SimpleDateFormat sdfMin = new SimpleDateFormat("mm");

        int hour = Integer.parseInt(sdfHour.format(nowTime));
        int min = Integer.parseInt(sdfMin.format(nowTime));

        int thisDayOfWeek = DateUtil.thisDayOfWeek();

        int numberOfRows = (thisDayOfWeek - 2) == -1 ? 6 : thisDayOfWeek - 2;
        int positionNumber = (hour * 2) + (min >= 30 ? 1 : 0);

        return timePeriod.get(numberOfRows).contains(positionNumber);
    }

    @Test
    public void test2() {
        //JSONObject对象
        HashMap map = new HashMap();
        map.put("appProperty",1);
        System.out.println(JSON.parseObject(JSON.toJSONString(map)));
        //JSONArray对象
        Object str = "[{\"activityPositionId\":[96,51,73,98,77],\"appId\":93}]";
        System.out.println(JSON.parseArray(str.toString()));

    }
}
