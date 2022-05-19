package com.laiyw.micro.order.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = {
        "com.laiyw.micro.portal.api.client",
        "com.laiyw.micro.stock.api.client",
        "com.laiyw.micro.id.api.client",
        "com.laiyw.micro.notify.api.client",
})
public class OrderServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderServiceApplication.class, args);
    }

}
