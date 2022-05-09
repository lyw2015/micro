# Micro 微服务集成测试

## 使用版本
```
spring-boot: 2.6.3

spring-cloud: 2021.0.1

spring-cloud-alibaba: 2021.0.1.0
```

## 使用组件：  
网关：Gateway

配置中心&服务注册与发现：Nacos

RPC：OpenFeign

熔断限流：Sentinel

连接池：Druid

缓存：Redis

分布式事务：Seata

分布式锁：Redisson

分布式ID：[uid-generator](micro-modules/ms-id)

读写分离：[AbstractRoutingDataSource](micro-core/core-mybatis/src/main/java/com/laiyw/micro/mybatis/dynamic/DynamicDataSource.java) + [Mybatis Interceptor](micro-core/core-mybatis/src/main/java/com/laiyw/micro/mybatis/interceptor/DynamicDataSourceInterceptor.java)
