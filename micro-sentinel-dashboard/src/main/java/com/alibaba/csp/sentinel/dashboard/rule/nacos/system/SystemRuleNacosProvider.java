package com.alibaba.csp.sentinel.dashboard.rule.nacos.system;

import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.SystemRuleEntity;
import com.alibaba.csp.sentinel.dashboard.rule.DynamicRuleProvider;
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

@Component("systemRuleNacosProvider")
public class SystemRuleNacosProvider extends NacosCommonService implements DynamicRuleProvider<List<SystemRuleEntity>> {

    @Override
    protected String ruleType() {
        return RuleType.SYSTEM.getName();
    }

    @Override
    public List<SystemRuleEntity> getRules(String appName) throws Exception {
        return getData(appName, SystemRuleEntity.class);
    }

}
