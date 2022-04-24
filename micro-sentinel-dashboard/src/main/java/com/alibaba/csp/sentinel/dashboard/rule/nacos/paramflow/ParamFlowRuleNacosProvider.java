package com.alibaba.csp.sentinel.dashboard.rule.nacos.paramflow;

import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.ParamFlowRuleEntity;
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

@Component("paramFlowRuleNacosProvider")
public class ParamFlowRuleNacosProvider extends NacosCommonService implements DynamicRuleProvider<List<ParamFlowRuleEntity>> {

    @Override
    protected String ruleType() {
        return RuleType.PARAM_FLOW.getName();
    }

    @Override
    public List<ParamFlowRuleEntity> getRules(String appName) throws Exception {
        return getData(appName, ParamFlowRuleEntity.class);
    }

}
