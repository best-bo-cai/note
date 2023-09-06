package com.liu.utils.ruleengine;

import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.Expression;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class DefaultRuleEngine implements IRuleEngine {

    //    @Override
    public boolean execute(String expression, Map<String, Object> params) {
        // 目前暂时用google的表达式求值引擎，后续如果考虑性能，可能会自己写

        Expression compiledExp = AviatorEvaluator.compile(expression, true);

        Object execute = compiledExp.execute(params);

        return Boolean.parseBoolean(execute.toString());
    }


    public static void main(String[] args) {
        String expressionStr = "((" + 10.00 + "-" + 9.00 + ")" + "/" + 9.00 + ")*100 >10"; // 表达式字符串

        // 创建表达式对象
        Expression expression = AviatorEvaluator.compile(expressionStr);

        // 使用Aviator表达式求值引擎计算表达式的结果
        Object result = expression.execute();

        // 打印结果
        System.out.println("结果: " + result);

    }

}
