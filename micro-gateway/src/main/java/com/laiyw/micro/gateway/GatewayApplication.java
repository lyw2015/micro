package com.laiyw.micro.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GatewayApplication {

    public static void main(String[] args) {
        // 在集成网关时需要配置csp.sentinel.app.type=1，是因为心跳优先发送了appType，所以需要事先在发送心跳之前配置appType
        System.setProperty("csp.sentinel.app.type", "1");
        SpringApplication.run(GatewayApplication.class, args);
    }

}
