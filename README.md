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

* 使用Spring Boot重写百度的[uid-generator](https://github.com/baidu/uid-generator)
* 使用Feign以及HTTP方式暴露ID获取API
* 提取参数配置至yaml
```yaml
# UID Generator
uid-generator:
  # 对于并发数要求不高、期望长期使用的应用, 可增加timeBits位数, 减少seqBits位数.
  # 例如节点采取用完即弃的WorkerIdAssigner策略, 重启频率为12次/天, 那么配置成{"workerBits":23,"timeBits":31,"seqBits":9}时,
  # 可支持28个节点以整体并发量14400 UID/s的速度持续运行68年.

  # 对于节点重启频率频繁、期望长期使用的应用, 可增加workerBits和timeBits位数, 减少seqBits位数.
  # 例如节点采取用完即弃的WorkerIdAssigner策略, 重启频率为24*12次/天, 那么配置成{"workerBits":27,"timeBits":30,"seqBits":6}时,
  # 可支持37个节点以整体并发量2400 UID/s的速度持续运行34年.
  timeBits: 29
  workerBits: 21
  seqBits: 13
  epochStr: 2016-09-20
  # RingBuffer size扩容参数, 可提高UID生成的吞吐量.
  # 默认:3， 原bufferSize=8192, 扩容后bufferSize= 8192 << 3 = 65536
  boostPower: 3
  # 指定何时向RingBuffer中填充UID, 取值为百分比(0, 100), 默认为50
  # 举例: bufferSize=1024, paddingFactor=50 -> threshold=1024 * 50 / 100 = 512.
  # 当环上可用UID数量 < 512时, 将自动对RingBuffer进行填充补全
  paddingFactor: 30
  # 另外一种RingBuffer填充时机, 在Schedule线程中, 周期性检查填充
  # 默认:不配置此项, 即不实用Schedule线程. 如需使用, 请指定Schedule线程时间间隔, 单位:秒
  scheduleInterval: 60
```

读写分离：[AbstractRoutingDataSource](micro-core/core-mybatis/src/main/java/com/laiyw/micro/mybatis/dynamic/DynamicDataSource.java) + [Mybatis Interceptor](micro-core/core-mybatis/src/main/java/com/laiyw/micro/mybatis/interceptor/DynamicDataSourceInterceptor.java)

* 实现一主多从
* 实现多Slave下的获取方式配置；loop(轮询)、random(随机)
* 可动态配置数据源，无需重启
* 当有事务时默认使用Master库
* master库必须配置，slave库按需配置(key可随意配置但不能重复；eg: slave, slave02, 112_ms)
```yaml
spring:
  datasource:
    druid:
      # 获取slave数据源方式：轮询:loop、随机:random，slave数据源只有一个时不生效
      slave-change-strategy: loop
      data-sources:
        master:
          driver-class-name: com.mysql.cj.jdbc.Driver
          url: jdbc:mysql://127.0.0.1:3306/micro-stock?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=Asia/Shanghai&nullCatalogMeansCurrent=true
          username: root
          password: root
        slave:
          driver-class-name: com.mysql.cj.jdbc.Driver
          url: jdbc:mysql://192.168.47.112:3306/micro-stock?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=Asia/Shanghai&nullCatalogMeansCurrent=true
          username: root
          password: root
        micro-mysql:
          driver-class-name: com.mysql.cj.jdbc.Driver
          url: jdbc:mysql://192.168.47.113:3306/micro-stock?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=Asia/Shanghai&nullCatalogMeansCurrent=true
          username: root
          password: root
```