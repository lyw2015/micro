package com.laiyw.micro.mq.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

/**
 * @ProjectName micro
 * @Author Laiyw
 * @CreateTime 2022/5/17 15:44
 * @Description 延时付款实现方式一(ttl + 死信队列)
 * 下单--->{@link #delayPaymentQueue()}---到达x-message-ttl值--->{@link #delayPaymentDlqQueue()}--->{@link #delayPaymentDlqQueueConsumer(String)}
 */
@Slf4j
@Configuration
public class PaymentDeadLetterConfig {

    /**
     * 延时付款队列
     *
     * @return
     */
    @Bean
    public Queue delayPaymentQueue() {
        return new Queue(
                MqConstants.QUEUE_DELAY_PAYMENT_NAME,
                true, false, false,
                new HashMap<String, Object>(3) {{
                    put("x-message-ttl", 1000 * 10);
                    put("x-dead-letter-exchange", MqConstants.EXCHANGE_DIRECT_DLX_NAME);
                    put("x-dead-letter-routing-key", MqConstants.ROUTING_KEY_DELAY_PAYMENT_DLK);
                }}
        );
    }

    /**
     * 延时付款死信队列
     *
     * @return
     */
    @Bean
    public Queue delayPaymentDlqQueue() {
        return new Queue(MqConstants.QUEUE_DELAY_PAYMENT_DLQ_NAME);
    }


    /**
     * Direct死信交换器
     *
     * @return
     */
    @Bean
    public DirectExchange directExchangeDlx() {
        return new DirectExchange(MqConstants.EXCHANGE_DIRECT_DLX_NAME);
    }


    /**
     * 绑定死信交换器和延时付款死信队列
     *
     * @return
     */
    @Bean
    public Binding bindingDirectDlx() {
        return BindingBuilder
                .bind(delayPaymentDlqQueue())
                .to(directExchangeDlx())
                .with(MqConstants.ROUTING_KEY_DELAY_PAYMENT_DLK);
    }

    /**
     * 延时付款死信队列消费者
     *
     * @param msg
     */
    @RabbitListener(queues = MqConstants.QUEUE_DELAY_PAYMENT_DLQ_NAME)
    public void delayPaymentDlqQueueConsumer(String msg) {
        log.info("收到来自死信队列的消息：{}", msg);
    }
}
