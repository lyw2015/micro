package com.laiyw.micro.feign;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @ProjectName micro
 * @Author Laiyw
 * @CreateTime 2022/4/20 14:30
 * @Description TODO
 */

@ComponentScan(basePackages = {"com.laiyw.micro.feign"})
@Configuration
public class FeignConfiguration {

    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.HEADERS;
    }
}
