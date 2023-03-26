package com.liu.utils.json;

import com.alibaba.fastjson2.JSON;

public class JSONUtils {
    /**
     * JSON数组字符串转数组对象
     *
     * @param str JSON数组字符串
     * @return JSON数组对象
     */
    public static Object strParseArray(String str) {
        if (str == null || "".equals(str)) {
            return null;
        }
        return JSON.parseArray(str);
    }

    /**
     * JSON字符串转对象
     *
     * @param str JSON字符串
     * @return JSON对象
     */
    public static Object strParseObject(String str) {
        if (str == null || "".equals(str)) {
            return null;
        }
        return JSON.parseObject(str);
    }

    /**
     * JSON对象转字符串
     *
     * @param o JSON对象
     * @return JSON字符串
     */
    public static String objectToJSONString(Object o) {
        if (o == null) {
            return "";
        }
        return JSON.toJSONString(o);
    }
}
