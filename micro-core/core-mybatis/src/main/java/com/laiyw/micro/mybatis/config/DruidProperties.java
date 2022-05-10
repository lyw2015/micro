package com.laiyw.micro.mybatis.config;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.Data;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * @ProjectName micro
 * @Author Laiyw
 * @CreateTime 2022/5/10 14:02
 * @Description TODO
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "spring.datasource.druid")
public class DruidProperties {

    /**
     * 数据源
     */
    private Map<String, DataSourceProperties> dataSources;
    /**
     * slave获取方式
     */
    private String slaveChangeStrategy;

    /**
     * 初始连接数
     */
    private int initialSize;
    /**
     * 最小连接池数量
     */
    private int minIdle;
    /**
     * 最大连接池数量
     */
    private int maxActive;
    /**
     * 配置获取连接等待超时的时间
     */
    private int maxWait;
    /**
     * 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒
     */
    private int timeBetweenEvictionRunsMillis;
    /**
     * 配置一个连接在池中最小生存的时间，单位是毫秒
     */
    private int minEvictableIdleTimeMillis;
    /**
     * 配置一个连接在池中最大生存的时间，单位是毫秒
     */
    private int maxEvictableIdleTimeMillis;
    /**
     * 用来检测连接是否有效的sql，要求是一个查询语句，常用select 'x'。如果validationQuery为null，testOnBorrow、testOnReturn、testWhileIdle都不会起作用
     */
    private String validationQuery;
    /**
     * 建议配置为true，不影响性能，并且保证安全性。申请连接的时候检测，如果空闲时间大于timeBetweenEvictionRunsMillis，执行validationQuery检测连接是否有效
     */
    private boolean testWhileIdle;
    /**
     * 申请连接时执行validationQuery检测连接是否有效，做了这个配置会降低性能
     */
    private boolean testOnBorrow;
    /**
     * 归还连接时执行validationQuery检测连接是否有效，做了这个配置会降低性能
     */
    private boolean testOnReturn;

    /**
     * 是否在自动回收超时连接的时候打印连接的超时错误
     */
    private boolean logAbandoned;
    /**
     * 是否自动回收超时连接
     */
    private boolean removeAbandoned;
    /**
     * 超时时间(以毫秒数为单位)
     */
    private long removeAbandonedTimeoutMillis;

    /**
     * 构建DruidDataSource属性
     *
     * @param datasource
     * @return DruidDataSource
     */
    public DruidDataSource buildProperties(DruidDataSource datasource) {
        datasource.setInitialSize(initialSize);
        datasource.setMaxActive(maxActive);
        datasource.setMinIdle(minIdle);

        datasource.setMaxWait(maxWait);

        datasource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);

        datasource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        datasource.setMaxEvictableIdleTimeMillis(maxEvictableIdleTimeMillis);

        datasource.setValidationQuery(validationQuery);
        datasource.setTestWhileIdle(testWhileIdle);
        datasource.setTestOnBorrow(testOnBorrow);
        datasource.setTestOnReturn(testOnReturn);

        datasource.setLogAbandoned(logAbandoned);
        datasource.setRemoveAbandoned(removeAbandoned);
        datasource.setRemoveAbandonedTimeoutMillis(removeAbandonedTimeoutMillis);
        return datasource;
    }
}
