package com.liu.utils.str;

import java.util.Map;

public class MessageTemplate {
    public static void main(String[] args) {
        String template = "有一个警报待查看\n" +
                "警报名称：${alarmName}\n" +
                "警报等级：${alarmLevel}\n" +
                "警报内容：${content}";
        Map<String, String> variables = Map.of("alarmName", "指标警报","alarmLevel","中级","content","有数BI调研：https://liuxin0007.youdata.163.com/");

        String message = replaceVariables(template, variables);
        System.out.println(message);
    }

    public static String replaceVariables(String template, Map<String, String> variables) {
        for (Map.Entry<String, String> entry : variables.entrySet()) {
            String variable = "${" + entry.getKey() + "}";
            String value = entry.getValue();
            template = template.replace(variable, value);
        }
        return template;
    }
}
