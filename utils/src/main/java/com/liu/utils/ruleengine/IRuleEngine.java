package com.liu.utils.ruleengine;

import java.util.Map;

public interface IRuleEngine {

    /**
     * 执行boolean值计算
     *
     * @param expression
     * @param params
     * @return
     */
    boolean execute(String expression, Map<String, Object> params);
}
