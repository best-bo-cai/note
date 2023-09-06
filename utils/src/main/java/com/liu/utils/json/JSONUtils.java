package com.liu.utils.json;

import com.alibaba.fastjson2.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JSONUtils {
    private static final Logger logger = LoggerFactory.getLogger(JSONUtils.class);

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
        boolean validJsonArray = JSON.isValidArray(str);
        if (!validJsonArray) {
            logger.error("无效的JSONArray字符串，str：{}", str);
            throw new RuntimeException("无效的JSONArray字符串");
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
        boolean validJson = JSON.isValidObject(str);
        if (!validJson) {
            logger.error("无效的JSON字符串，str：{}", str);
            throw new RuntimeException("无效的JSON字符串");
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
