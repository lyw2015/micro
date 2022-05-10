package com.laiyw.micro.mybatis.dynamic;

import com.laiyw.micro.mybatis.enums.DynamicDataSourceType;
import lombok.extern.slf4j.Slf4j;

/**
 * @ProjectName micro
 * @Author Laiyw
 * @CreateTime 2022/5/9 9:53
 * @Description TODO
 */
@Slf4j
public class DynamicDatasourceHolder {

    private final static ThreadLocal<DynamicDataSourceType> THREAD_LOCAL = new ThreadLocal<>();

    public static void setDatasourceType(DynamicDataSourceType dataSourceType) {
        log.debug("设置数据源为: {}", dataSourceType);
        THREAD_LOCAL.set(dataSourceType);
    }

    public static DynamicDataSourceType getDatasourceType() {
        return THREAD_LOCAL.get();
    }

    public static void clearDatasourceType() {
        THREAD_LOCAL.remove();
    }
}
