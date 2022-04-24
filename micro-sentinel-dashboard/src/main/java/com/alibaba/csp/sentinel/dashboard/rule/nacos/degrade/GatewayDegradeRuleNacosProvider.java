package com.alibaba.csp.sentinel.dashboard.rule.nacos.degrade;

import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.DegradeRuleEntity;
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

@Component("degradeRuleNacosProvider")
public class GatewayDegradeRuleNacosProvider extends NacosCommonService implements DynamicRuleProvider<List<DegradeRuleEntity>> {

    @Override
    protected String ruleType() {
        return RuleType.DEGRADE.getName();
    }

    @Override
    public List<DegradeRuleEntity> getRules(String appName) throws Exception {
        return getData(appName, DegradeRuleEntity.class);
    }

}
