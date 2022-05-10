package com.laiyw.micro.mybatis.provide;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.laiyw.micro.mybatis.enums.DynamicDataSourceType;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.MapUtils;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;

import java.util.HashMap;
import java.util.Map;

/**
 * @ProjectName micro
 * @Author Laiyw
 * @CreateTime 2022/5/10 9:02
 * @Description TODO
 */

@Slf4j
public abstract class DataSourceProvide {

    public Map<Object, Object> initialize() {
        Map<String, DataSourceProperties> dataSourcePropertiesMap = provide();
        if (MapUtils.isEmpty(dataSourcePropertiesMap)) {
            throw new IllegalArgumentException("请配置数据源");
        }
        if (!dataSourcePropertiesMap.containsKey(DynamicDataSourceType.master.name())) {
            throw new IllegalArgumentException("请指定key为master的数据源");
        }
        Map<Object, Object> targetDataSources = new HashMap<>(dataSourcePropertiesMap.size());
        final int[] slaveNumber = {0};
        dataSourcePropertiesMap.forEach((nodeType, properties) -> {
            log.debug("开始构建数据源: {}", nodeType);
            DruidDataSource druidDataSource = buildDruidDataSource(properties);
            if (!DynamicDataSourceType.master.name().equalsIgnoreCase(nodeType)) {
                nodeType = String.format("%s_%d", DynamicDataSourceType.slave.name(), ++slaveNumber[0]);
            }
            log.debug("初始化[{}]数据源完成", nodeType);
            targetDataSources.put(nodeType, druidDataSource);

        });
        return targetDataSources;
    }

    protected DruidDataSource buildDruidDataSource(DataSourceProperties properties) {
        DruidDataSource druidDataSource = DruidDataSourceBuilder.create().build();
        druidDataSource.setDriverClassName(properties.getDriverClassName());
        druidDataSource.setUrl(properties.getUrl());
        druidDataSource.setUsername(properties.getUsername());
        druidDataSource.setPassword(properties.getPassword());
        return druidDataSource;
    }

    /**
     * 获取数据源配置信息
     *
     * @return Map
     */
    public abstract Map<String, DataSourceProperties> provide();
}
