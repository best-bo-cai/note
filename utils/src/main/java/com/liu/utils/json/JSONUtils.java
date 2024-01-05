package com.liu.utils.json;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JSONUtils {
    private static final Logger logger = LoggerFactory.getLogger(JSONUtils.class);

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

    /**
     * 解析任意JSON串，解析返回后的JSON
     *
     * @param str 需要解析的JSON串
     * @return 解析后的JSON串，如果不是JSON，则原样返回。
     */
    public static Object parseJson(String str) {
        boolean validJson = JSON.isValid(str);
        if (validJson) {
            Object obj = JSON.parse(str);
            if (obj instanceof JSONObject) {
                return JSONObject.parseObject(obj.toString());
            } else if (obj instanceof JSONArray) {
                return JSONArray.parseArray(obj.toString());
            } else {
                return null;
            }
        } else {
            logger.error("无效的JSON字符串，str：{}", str);
            throw new RuntimeException("无效的JSON字符串");
        }
    }
}
