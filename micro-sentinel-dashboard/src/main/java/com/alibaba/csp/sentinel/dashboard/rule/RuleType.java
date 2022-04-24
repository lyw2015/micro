package com.alibaba.csp.sentinel.dashboard.rule;

/**
 * @ProjectName micro
 * @Author Laiyw
 * @CreateTime 2022/4/22 9:15
 * @Description TODO
 */

public enum RuleType {

    /**
     * 规则类型
     */
    FLOW("flow", "流控规则"),
    DEGRADE("degrade", "熔断规则"),
    PARAM_FLOW("param-flow", "热点规则"),
    SYSTEM("system", "系统规则"),
    AUTHORITY("authority", "授权规则"),
    GW_FLOW("gw-flow", "网关流控规则"),
    GW_API_GROUP("gw-api-group", "网关API管理");

    private final String name;
    private final String describe;

    RuleType(String name, String describe) {
        this.name = name;
        this.describe = describe;
    }

    public String getName() {
        return name;
    }

    public String getDescribe() {
        return describe;
    }
}
