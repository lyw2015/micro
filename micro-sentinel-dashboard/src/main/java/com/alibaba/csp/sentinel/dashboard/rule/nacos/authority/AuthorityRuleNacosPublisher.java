package com.alibaba.csp.sentinel.dashboard.rule.nacos.authority;

import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.AuthorityRuleEntity;
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
@Component("authorityRuleNacosPublisher")
public class AuthorityRuleNacosPublisher extends NacosCommonService implements DynamicRulePublisher<List<AuthorityRuleEntity>> {

    @Override
    protected String ruleType() {
        return RuleType.AUTHORITY.getName();
    }

    @Override
    public void publish(String app, List<AuthorityRuleEntity> rules) throws Exception {
        pushData(app, rules);
    }
}
