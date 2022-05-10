package com.laiyw.micro.mybatis.dynamic;

import com.laiyw.micro.mybatis.enums.DynamicDataSourceType;
import com.laiyw.micro.mybatis.provide.DataSourceProvide;
import com.laiyw.micro.mybatis.strategy.SlaveChangeStrategyFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @ProjectName micro
 * @Author Laiyw
 * @CreateTime 2022/5/9 9:56
 * @Description TODO
 */
@Slf4j
@RefreshScope
@Component
public class DynamicDataSource extends AbstractRoutingDataSource {

    private static Map<Object, Object> targetDataSources = new HashMap<>();
    private static int slaveNumber = 0;
    @Value("${spring.datasource.druid.slave-change-strategy:loop}")
    private String slaveChangeStrategy;

    @Autowired
    public void setProvide(DataSourceProvide provide) {
        targetDataSources = provide.initialize();
        slaveNumber = targetDataSources.size() - 1;
    }

    @Override
    protected Object determineCurrentLookupKey() {
        DynamicDataSourceType datasourceType = DynamicDatasourceHolder.getDatasourceType();
        if (null == datasourceType || DynamicDataSourceType.master.equals(datasourceType)) {
            return DynamicDataSourceType.master;
        }
        if (slaveNumber == 0) {
            log.debug("无有效Slave数据源，改用Master数据源");
            return DynamicDataSourceType.master;
        }
        int slaveNum = 1;
        if (slaveNumber > 1) {
            log.debug("Slave节点选举策略: {}", slaveChangeStrategy);
            slaveNum = SlaveChangeStrategyFactory.getSlaveChangeStrategy(slaveChangeStrategy).selectSlaveNode(slaveNumber);
        }
        return targetDataSources.get(String.format("%s_%d", DynamicDataSourceType.slave.name(), slaveNum));
    }

    @Override
    public void afterPropertiesSet() {
        setDefaultTargetDataSource(targetDataSources.get(DynamicDataSourceType.master.name()));
        setTargetDataSources(targetDataSources);
        super.afterPropertiesSet();
    }
}
