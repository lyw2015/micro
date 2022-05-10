package com.laiyw.micro.mybatis.provide;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
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
@ConfigurationProperties(prefix = "spring.datasource.druid")
public class YamlDataSourceProvide extends DataSourceProvide {

    private Map<String, DataSourceProperties> dataSources;

    @Override
    public Map<String, DataSourceProperties> provide() {
        return dataSources;
    }

    public Map<String, DataSourceProperties> getDataSources() {
        return dataSources;
    }

    public void setDataSources(Map<String, DataSourceProperties> dataSources) {
        this.dataSources = dataSources;
    }
}
