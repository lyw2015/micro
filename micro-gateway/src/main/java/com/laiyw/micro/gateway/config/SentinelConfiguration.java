package com.laiyw.micro.gateway.config;

import com.alibaba.csp.sentinel.adapter.gateway.sc.callback.BlockRequestHandler;
import com.laiyw.micro.gateway.handler.SentinelBlockRequestHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ProjectName micro
 * @Author Laiyw
 * @CreateTime 2022/4/22 14:40
 * @Description TODO
 */

@Configuration
public class SentinelConfiguration {

    @Bean
    public BlockRequestHandler blockRequestHandler() {
        return new SentinelBlockRequestHandler();
    }
}
