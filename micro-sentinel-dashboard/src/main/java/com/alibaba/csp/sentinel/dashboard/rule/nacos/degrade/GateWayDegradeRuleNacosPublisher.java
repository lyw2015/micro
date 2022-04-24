package com.alibaba.csp.sentinel.dashboard.rule.nacos.degrade;

import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.DegradeRuleEntity;
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
@Component("degradeRuleNacosPublisher")
public class GateWayDegradeRuleNacosPublisher extends NacosCommonService implements DynamicRulePublisher<List<DegradeRuleEntity>> {

    @Override
    protected String ruleType() {
        return RuleType.DEGRADE.getName();
    }

    @Override
    public void publish(String app, List<DegradeRuleEntity> rules) throws Exception {
        pushData(app, rules);
    }
}
