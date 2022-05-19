package com.laiyw.micro.mq.config;

import com.laiyw.micro.mq.config.rabbit.MqConstants;
import com.laiyw.micro.mq.config.rabbit.callback.ToExchangeCallback;
import com.laiyw.micro.mq.config.rabbit.callback.ToQueueCallback;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * @ProjectName micro
 * @Author Laiyw
 * @CreateTime 2022/5/19 12:04
 * @Description TODO
 */
@Slf4j
@Configuration
public class RabbitConfig {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostConstruct
    public void initialize() {
        // rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        rabbitTemplate.setConfirmCallback(new ToExchangeCallback());
        rabbitTemplate.setReturnsCallback(new ToQueueCallback());
    }

    @Bean
    public Queue notifyQueue() {
        return new Queue(MqConstants.QUEUE_NOTIFY_NAME);
    }

    @Bean
    public Binding bindingDirectNotify(DirectExchange directExchange) {
        return BindingBuilder.bind(notifyQueue()).to(directExchange).with(MqConstants.ROUTING_KEY_NOTIFY);
    }

    @Bean
    public Binding bindingTopicNotify(TopicExchange topicExchange) {
        return BindingBuilder.bind(notifyQueue()).to(topicExchange).with("notify.#");
    }

    @Bean
    public Binding bindingFanoutNotify(FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(notifyQueue()).to(fanoutExchange);
    }
}
