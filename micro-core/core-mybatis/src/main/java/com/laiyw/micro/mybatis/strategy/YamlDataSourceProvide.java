package com.laiyw.micro.mybatis.strategy;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.laiyw.micro.mybatis.dynamic.DynamicDataSource;
import com.laiyw.micro.mybatis.enums.DynamicDataSourceType;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @ProjectName micro
 * @Author Laiyw
 * @CreateTime 2022/5/9 20:36
 * @Description TODO
 */

@Slf4j
@Component
public class YamlDataSourceProvide {

    public static Map<Object, Object> targetDataSources = new HashMap<>();
    @Value("${spring.datasource.druid.data-sources}")
    private Map<String, DataSourceProperties> dataSources;
    @Autowired
    private DynamicDataSource dynamicDataSource;
    public static int slaveNumber = 0;

    @PostConstruct
    public void initPor() {
        if (MapUtils.isEmpty(dataSources) || !dataSources.containsKey(DynamicDataSourceType.master.name())) {
            throw new IllegalArgumentException("请指定Master数据源");
        }
        dataSources.forEach((nodeType, properties) -> {
            DataSource dataSource = buildDataSource(properties);
            if (!DynamicDataSourceType.master.name().equalsIgnoreCase(nodeType)) {
                nodeType = String.format("%s%d", nodeType, ++slaveNumber);
            }
            addDataSource(nodeType, dataSource);
        });
    }

    public void addDataSource(String name, DataSource dataSource) {
        targetDataSources.put(name, dataSource);
        dynamicDataSource.setTargetDataSources(targetDataSources);
        dynamicDataSource.afterPropertiesSet();
        log.info("添加数据源: {}", name);
    }

    private DataSource buildDataSource(DataSourceProperties properties) {
        DruidDataSource druidDataSource = DruidDataSourceBuilder.create().build();
        druidDataSource.setDriverClassName(properties.getDriverClassName());
        druidDataSource.setUrl(properties.getUrl());
        druidDataSource.setUsername(properties.getUsername());
        druidDataSource.setPassword(properties.getPassword());
        return druidDataSource;
    }
}
