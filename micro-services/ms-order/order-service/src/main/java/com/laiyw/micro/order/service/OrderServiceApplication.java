package com.laiyw.micro.order.service;

import com.laiyw.micro.feign.FeignConfiguration;
import com.laiyw.micro.mybatis.MybatisPlusConfig;
import com.laiyw.micro.seata.SeataConfiguration;
import com.laiyw.micro.sentinel.SentinelConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({
        MybatisPlusConfig.class,
        FeignConfiguration.class,
        SentinelConfiguration.class,
        SeataConfiguration.class
})
@EnableFeignClients(basePackages = {
        "com.laiyw.micro.portal.api.client"
})
public class OrderServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderServiceApplication.class, args);
    }

}
