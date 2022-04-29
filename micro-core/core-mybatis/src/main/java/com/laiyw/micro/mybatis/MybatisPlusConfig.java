package com.laiyw.micro.mybatis;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.BlockAttackInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.laiyw.micro.mybatis.handler.CustomMetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @ProjectName micro
 * @Author Laiyw
 * @CreateTime 2022/4/25 15:03
 * @Description TODO
 */

@Slf4j
@Configuration
@AutoConfigureBefore({DruidDataSourceAutoConfigure.class})
@ComponentScan(basePackages = "com.laiyw.micro.mybatis")
public class MybatisPlusConfig {

    /**
     * 插件
     *
     * @return MybatisPlusInterceptor
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        log.info("register mybatis-plus interceptor");
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor());
        interceptor.addInnerInterceptor(new BlockAttackInnerInterceptor());
        return interceptor;
    }

    /**
     * 自动填充
     *
     * @return MetaObjectHandler
     */
    @Bean
    public MetaObjectHandler metaObjectHandler() {
        return new CustomMetaObjectHandler();
    }
}
