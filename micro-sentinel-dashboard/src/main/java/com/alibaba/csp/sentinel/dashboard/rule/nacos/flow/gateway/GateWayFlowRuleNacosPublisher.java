package com.alibaba.csp.sentinel.dashboard.rule.nacos.flow.gateway;

import com.alibaba.csp.sentinel.dashboard.datasource.entity.gateway.GatewayFlowRuleEntity;
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
@Component("gatewayFlowRuleNacosPublisher")
public class GateWayFlowRuleNacosPublisher extends NacosCommonService implements DynamicRulePublisher<List<GatewayFlowRuleEntity>> {

    @Override
    protected String ruleType() {
        return RuleType.GW_FLOW.getName();
    }

    @Override
    public void publish(String app, List<GatewayFlowRuleEntity> rules) throws Exception {
        pushData(app, rules);
    }
}
