# Sentinel Dashboard规则持久至Nacos，且支持双向同步

## 网关接入配置

### xml

```xml

<dependencies>
    <dependency>
        <groupId>com.alibaba.cloud</groupId>
        <artifactId>spring-cloud-starter-alibaba-sentinel</artifactId>
    </dependency>
    <dependency>
        <groupId>com.alibaba.cloud</groupId>
        <artifactId>spring-cloud-alibaba-sentinel-gateway</artifactId>
    </dependency>
    <dependency>
        <groupId>com.alibaba.csp</groupId>
        <artifactId>sentinel-datasource-nacos</artifactId>
    </dependency>
</dependencies>
```

### yaml

```yaml
spring:
  cloud:
    sentinel:
      eager: true
      filter:
        enabled: true
      transport:
        # 启动sentinel-dashboard添加的参数，若无改动则默认为127.0.0.1:8858
        dashboard: 127.0.0.1:8858
        port: 8719
      datasource:
        # API管理
        gw-api-group:
          nacos:
            server-addr: ${spring.cloud.nacos.config.server-addr}
            namespace: ${spring.cloud.nacos.config.namespace}
            group-id: SENTINEL_GROUP
            data-id: ${spring.application.name}-gw-api-group
            rule-type: gw-api-group
            data-type: json
        # 流控规则
        gw-flow:
          nacos:
            server-addr: ${spring.cloud.nacos.config.server-addr}
            namespace: ${spring.cloud.nacos.config.namespace}
            group-id: SENTINEL_GROUP
            data-id: ${spring.application.name}-flow
            rule-type: gw-flow
            data-type: json
        # 熔断规则
        degrade:
          nacos:
            server-addr: ${spring.cloud.nacos.config.server-addr}
            namespace: ${spring.cloud.nacos.config.namespace}
            group-id: SENTINEL_GROUP
            data-id: ${spring.application.name}-degrade
            rule-type: degrade
            data-type: json
        # 系统规则
        system:
          nacos:
            server-addr: ${spring.cloud.nacos.config.server-addr}
            namespace: ${spring.cloud.nacos.config.namespace}
            group-id: SENTINEL_GROUP
            data-id: ${spring.application.name}-system
            rule-type: system
            data-type: json
```

## 服务接入配置

### xml

```xml

<dependencies>
    <dependency>
        <groupId>com.alibaba.cloud</groupId>
        <artifactId>spring-cloud-starter-alibaba-sentinel</artifactId>
    </dependency>
    <dependency>
        <groupId>com.alibaba.csp</groupId>
        <artifactId>sentinel-datasource-nacos</artifactId>
    </dependency>
</dependencies>
```

### yaml

```yaml
spring:
  cloud:
    sentinel:
      enabled: true
      eager: true
      transport:
        # 启动sentinel-dashboard添加的参数，若无改动则默认为127.0.0.1:8858
        dashboard: 127.0.0.1:8858
      datasource:
        # 流控规则
        flow:
          nacos:
            server-addr: ${spring.cloud.nacos.config.server-addr}
            namespace: ${spring.cloud.nacos.config.namespace}
            group-id: SENTINEL_GROUP
            data-id: ${spring.application.name}-flow
            rule-type: flow
            data-type: json
        # 熔断规则
        degrade:
          nacos:
            server-addr: ${spring.cloud.nacos.config.server-addr}
            namespace: ${spring.cloud.nacos.config.namespace}
            group-id: SENTINEL_GROUP
            data-id: ${spring.application.name}-degrade
            rule-type: degrade
            data-type: json
        # 热点规则
        param-flow:
          nacos:
            server-addr: ${spring.cloud.nacos.config.server-addr}
            namespace: ${spring.cloud.nacos.config.namespace}
            group-id: SENTINEL_GROUP
            data-id: ${spring.application.name}-param-flow
            rule-type: param-flow
            data-type: json
        # 授权规则
        authority:
          nacos:
            server-addr: ${spring.cloud.nacos.config.server-addr}
            namespace: ${spring.cloud.nacos.config.namespace}
            group-id: SENTINEL_GROUP
            data-id: ${spring.application.name}-authority
            rule-type: authority
            data-type: json
        # 系统规则
        system:
          nacos:
            server-addr: ${spring.cloud.nacos.config.server-addr}
            namespace: ${spring.cloud.nacos.config.namespace}
            group-id: SENTINEL_GROUP
            data-id: ${spring.application.name}-system
            rule-type: system
            data-type: json

feign:
  sentinel:
    enabled: true
```