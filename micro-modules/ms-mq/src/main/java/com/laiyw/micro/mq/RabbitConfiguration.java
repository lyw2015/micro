package com.laiyw.micro.mq;

import com.laiyw.micro.mq.config.MqConstants;
import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @ProjectName micro
 * @Author Laiyw
 * @CreateTime 2022/5/16 15:07
 * @Description TODO
 */

@Configuration
@ComponentScan({"com.laiyw.micro.mq"})
public class RabbitConfiguration {

    // @Bean
    public MessageConverter jackson2JsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
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
    public Binding bindingFanoutSms(FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(notifyQueue()).to(fanoutExchange);
    }
}
