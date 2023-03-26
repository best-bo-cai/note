package com.gudian.base.ruleengine;

import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.Expression;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class DefaultRuleEngine implements IRuleEngine {

    @Override
    public boolean execute(String expression, Map<String, Object> params) {
        // 目前暂时用google的表达式求值引擎，后续如果考虑性能，可能会自己写

        Expression compiledExp = AviatorEvaluator.compile(expression,true);

        Object execute = compiledExp.execute(params);

        return Boolean.parseBoolean(execute.toString());
    }
}
