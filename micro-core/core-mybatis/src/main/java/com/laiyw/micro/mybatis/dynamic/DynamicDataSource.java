package com.laiyw.micro.mybatis.dynamic;

import com.laiyw.micro.mybatis.enums.DynamicDataSourceType;
import com.laiyw.micro.mybatis.strategy.SlaveChangeStrategyFactory;
import com.laiyw.micro.mybatis.strategy.YamlDataSourceProvide;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @ProjectName micro
 * @Author Laiyw
 * @CreateTime 2022/5/9 9:56
 * @Description TODO
 */
@RefreshScope
@Component
public class DynamicDataSource extends AbstractRoutingDataSource {

    @Value("${spring.datasource.druid.slave-change-strategy}")
    private String slaveChangeStrategy;

    @Override
    protected Object determineCurrentLookupKey() {
        DynamicDataSourceType datasourceType = DynamicDatasourceHolder.getDatasourceType();
        if (DynamicDataSourceType.master.equals(datasourceType)) {
            return datasourceType;
        }
        int index = SlaveChangeStrategyFactory.getSlaveChangeStrategy(slaveChangeStrategy).select(YamlDataSourceProvide.slaveNumber);
        return YamlDataSourceProvide.targetDataSources.get("slave" + index);
    }

    @Override
    public void afterPropertiesSet() {
        Map<Object, Object> targetDataSources = YamlDataSourceProvide.targetDataSources;
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        dynamicDataSource.setDefaultTargetDataSource(targetDataSources.get(DynamicDataSourceType.master.name()));
        dynamicDataSource.setTargetDataSources(targetDataSources);
        super.afterPropertiesSet();
    }
}
