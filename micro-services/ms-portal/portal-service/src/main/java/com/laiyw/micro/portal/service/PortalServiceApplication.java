package com.laiyw.micro.portal.service;

import com.laiyw.micro.feign.FeignConfiguration;
import com.laiyw.micro.mybatis.MybatisPlusConfig;
import com.laiyw.micro.seata.SeataConfiguration;
import com.laiyw.micro.sentinel.SentinelConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

/**
 * @author admin
 */
@SpringBootApplication
@Import({
        MybatisPlusConfig.class,
        FeignConfiguration.class,
        SentinelConfiguration.class,
        SeataConfiguration.class
})
public class PortalServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PortalServiceApplication.class, args);
    }

}
