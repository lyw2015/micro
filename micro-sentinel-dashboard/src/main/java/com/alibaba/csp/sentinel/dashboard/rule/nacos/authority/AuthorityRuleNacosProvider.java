package com.alibaba.csp.sentinel.dashboard.rule.nacos.authority;

import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.AuthorityRuleEntity;
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

@Component("authorityRuleNacosProvider")
public class AuthorityRuleNacosProvider extends NacosCommonService implements DynamicRuleProvider<List<AuthorityRuleEntity>> {

    @Override
    protected String ruleType() {
        return RuleType.AUTHORITY.getName();
    }

    @Override
    public List<AuthorityRuleEntity> getRules(String appName) throws Exception {
        return getData(appName, AuthorityRuleEntity.class);
    }

}
