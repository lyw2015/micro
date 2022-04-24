# 网关接入配置
```
sentinel:
      eager: true
      transport:
        dashboard: 127.0.0.1:8858
      datasource:
        # flow, degrade, param-flow, system, authority, gw-flow, gw-api-group
        gw-api-group:
          nacos:
            server-addr: ${spring.cloud.nacos.config.server-addr}
            namespace: ${spring.cloud.nacos.config.namespace}
            group-id: SENTINEL_GROUP
            data-id: ${spring.application.name}-gw-api-group
            rule-type: gw-api-group
            data-type: json
        gw-flow:
          nacos:
            server-addr: ${spring.cloud.nacos.config.server-addr}
            namespace: ${spring.cloud.nacos.config.namespace}
            group-id: SENTINEL_GROUP
            data-id: ${spring.application.name}-flow
            rule-type: gw-flow
            data-type: json
        degrade:
          nacos:
            server-addr: ${spring.cloud.nacos.config.server-addr}
            namespace: ${spring.cloud.nacos.config.namespace}
            group-id: SENTINEL_GROUP
            data-id: ${spring.application.name}-degrade
            rule-type: degrade
            data-type: json
        system:
          nacos:
            server-addr: ${spring.cloud.nacos.config.server-addr}
            namespace: ${spring.cloud.nacos.config.namespace}
            group-id: SENTINEL_GROUP
            data-id: ${spring.application.name}-system
            rule-type: system
            data-type: json
      filter:
        enabled: true
```