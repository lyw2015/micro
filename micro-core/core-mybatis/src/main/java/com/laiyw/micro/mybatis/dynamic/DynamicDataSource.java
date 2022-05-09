package com.laiyw.micro.mybatis.dynamic;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @ProjectName micro
 * @Author Laiyw
 * @CreateTime 2022/5/9 9:56
 * @Description TODO
 */

public class DynamicDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        return DynamicDatasourceHolder.getDatasourceType();
    }
}
