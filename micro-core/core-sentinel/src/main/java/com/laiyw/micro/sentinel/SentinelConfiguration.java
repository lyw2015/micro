package com.laiyw.micro.sentinel;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.BlockExceptionHandler;
import com.laiyw.micro.sentinel.handler.SentinelBlockRequestHandler;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ProjectName micro
 * @Author Laiyw
 * @CreateTime 2022/4/26 14:15
 * @Description TODO
 */
@Configuration
@ConditionalOnProperty(value = "spring.cloud.sentinel.enabled", havingValue = "true")
public class SentinelConfiguration {

    @Bean
    public BlockExceptionHandler blockExceptionHandler() {
        return new SentinelBlockRequestHandler();
    }
}
