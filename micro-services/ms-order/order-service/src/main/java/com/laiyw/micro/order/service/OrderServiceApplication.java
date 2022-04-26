package com.laiyw.micro.order.service;

import com.laiyw.micro.mybatis.MybatisPlusConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({MybatisPlusConfig.class})
@EnableFeignClients(basePackages = {"com.laiyw.micro.portal.api.client"})
public class OrderServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderServiceApplication.class, args);
    }

}
