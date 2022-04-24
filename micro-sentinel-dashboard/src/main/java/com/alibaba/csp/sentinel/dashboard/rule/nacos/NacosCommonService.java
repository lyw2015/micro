package com.alibaba.csp.sentinel.dashboard.rule.nacos;

import com.alibaba.csp.sentinel.dashboard.config.DashboardConfig;
import com.alibaba.csp.sentinel.util.AssertUtil;
import com.alibaba.csp.sentinel.util.StringUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.ConfigType;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName micro
 * @Author Laiyw
 * @CreateTime 2022/4/21 15:43
 * @Description TODO
 */

public abstract class NacosCommonService {

    @Autowired
    private NacosConfig nacosConfig;
    @Autowired
    private ConfigService configService;

    protected <T> List<T> getData(String appName, Class<T> clazz) throws Exception {
        String rules = configService.getConfig(
                getDataId(appName),
                getGroupId(),
                3000
        );
        if (StringUtil.isEmpty(rules)) {
            return new ArrayList<>();
        }
        return JSON.parseArray(rules, clazz);
    }

    protected void pushData(String appName, List<?> rules) throws Exception {
        AssertUtil.notEmpty(appName, "app name cannot be empty");
        if (null == rules) {
            return;
        }
        configService.publishConfig(
                getDataId(appName),
                getGroupId(),
                JSON.toJSONString(rules, true),
                ConfigType.JSON.getType()
        );
    }

    private String getGroupId() {
        return StringUtils.defaultIfBlank(DashboardConfig.getNacosGroupId(), nacosConfig.getGroupId());
    }

    private String getDataId(String appName) {
        return String.format("%s-%s", appName, ruleType());
    }

    protected abstract String ruleType();
}
