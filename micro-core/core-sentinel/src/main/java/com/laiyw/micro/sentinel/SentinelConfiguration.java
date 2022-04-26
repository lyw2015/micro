package com.laiyw.micro.sentinel;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.BlockExceptionHandler;
import com.laiyw.micro.sentinel.handler.SentinelBlockRequestHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @ProjectName micro
 * @Author Laiyw
 * @CreateTime 2022/4/26 14:15
 * @Description TODO
 */

@ComponentScan(basePackages = "com.laiyw.micro.sentinel")
@Configuration
public class SentinelConfiguration {

    @Bean
    public BlockExceptionHandler blockExceptionHandler() {
        return new SentinelBlockRequestHandler();
    }
}
