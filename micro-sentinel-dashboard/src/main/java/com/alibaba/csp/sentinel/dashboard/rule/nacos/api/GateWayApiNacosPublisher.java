package com.alibaba.csp.sentinel.dashboard.rule.nacos.api;

import com.alibaba.csp.sentinel.dashboard.datasource.entity.gateway.ApiDefinitionEntity;
import com.alibaba.csp.sentinel.dashboard.rule.DynamicRulePublisher;
import com.alibaba.csp.sentinel.dashboard.rule.RuleType;
import com.alibaba.csp.sentinel.dashboard.rule.nacos.NacosCommonService;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @ProjectName micro
 * @Author Laiyw
 * @CreateTime 2022/4/21 12:08
 * @Description TODO
 */
@Component("gatewayApiNacosPublisher")
public class GateWayApiNacosPublisher extends NacosCommonService implements DynamicRulePublisher<List<ApiDefinitionEntity>> {

    @Override
    protected String ruleType() {
        return RuleType.GW_API_GROUP.getName();
    }

    @Override
    public void publish(String app, List<ApiDefinitionEntity> rules) throws Exception {
        pushData(app, rules);
    }
}
