package json;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.liu.utils.json.JSONUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class JSONUtilsTest {
    @Test
    void testParseJson() {
        // 测试解析 JSONObject
        String jsonObjectStr = "{\"name\":\"John\", \"age\":30}";
        Object jsonObject = JSONUtils.parseJson(jsonObjectStr);
        Assertions.assertTrue(jsonObject instanceof JSONObject);

        // 测试解析 JSONArray
        String jsonArrayStr = "[\"apple\", \"banana\", \"cherry\"]";
        Object jsonArray = JSONUtils.parseJson(jsonArrayStr);
        Assertions.assertTrue(jsonArray instanceof JSONArray);

        // 测试解析非 JSON 字符串
        String nonJsonStr = "This is not a JSON string.";
        try {
            JSONUtils.parseJson(nonJsonStr);
        } catch (RuntimeException e) {
            Assertions.assertEquals("无效的JSON字符串", e.getMessage());
        }
        // 测试解析 null
        try {
            JSONUtils.parseJson(null);
        } catch (RuntimeException e) {
            Assertions.assertEquals("无效的JSON字符串", e.getMessage());
        }
        // 测试解析 ""
        try {
            JSONUtils.parseJson("");
        } catch (RuntimeException e) {
            Assertions.assertEquals("无效的JSON字符串", e.getMessage());
        }
    }

}