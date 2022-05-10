package com.laiyw.micro.mybatis.provide;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @ProjectName micro
 * @Author Laiyw
 * @CreateTime 2022/5/9 20:36
 * @Description TODO
 */

@Slf4j
@Component
public class YamlDataSourceProvide extends DataSourceProvide {

    @Override
    public Map<String, DataSourceProperties> provide() {
        return druidProperties.getDataSources();
    }

}
