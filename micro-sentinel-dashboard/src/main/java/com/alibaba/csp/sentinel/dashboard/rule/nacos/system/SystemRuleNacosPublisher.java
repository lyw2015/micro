package com.alibaba.csp.sentinel.dashboard.rule.nacos.system;

import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.SystemRuleEntity;
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
@Component("systemRuleNacosPublisher")
public class SystemRuleNacosPublisher extends NacosCommonService implements DynamicRulePublisher<List<SystemRuleEntity>> {

    @Override
    protected String ruleType() {
        return RuleType.SYSTEM.getName();
    }

    @Override
    public void publish(String app, List<SystemRuleEntity> rules) throws Exception {
        pushData(app, rules);
    }
}
