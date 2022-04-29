package com.laiyw.micro.seata;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @ProjectName micro
 * @Author Laiyw
 * @CreateTime 2022/4/27 16:46
 * @Description TODO
 */

@Configuration
@ComponentScan(basePackages = "com.laiyw.micro.seata")
@ConditionalOnProperty(value = "seata.enabled", havingValue = "true")
public class SeataConfiguration {
}
