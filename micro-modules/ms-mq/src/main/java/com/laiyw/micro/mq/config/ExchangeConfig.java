package com.laiyw.micro.mq.config;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.HeadersExchange;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ProjectName micro
 * @Author Laiyw
 * @CreateTime 2022/5/16 16:09
 * @Description TODO
 */
@Configuration
public class ExchangeConfig {

    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange(MqConstants.EXCHANGE_FANOUT_NAME);
    }

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(MqConstants.EXCHANGE_DIRECT_NAME);
    }

    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(MqConstants.EXCHANGE_TOPIC_NAME);
    }

    @Bean
    public HeadersExchange headersExchange() {
        return new HeadersExchange(MqConstants.EXCHANGE_HEADERS_NAME);
    }
}
