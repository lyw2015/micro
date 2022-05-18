package com.laiyw.micro.mq.config.delay;

import com.laiyw.micro.mq.config.MqConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.CustomExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

/**
 * @ProjectName micro
 * @Author Laiyw
 * @CreateTime 2022/5/17 17:44
 * @Description 延时付款实现方式二(使用插件rabbitmq_delayed_message_exchange)
 */
@Slf4j
@Configuration
public class PaymentDelayedConfig {

    @Bean
    public CustomExchange exchangeCustomDirectDelayed() {
        return new CustomExchange(
                MqConstants.EXCHANGE_CUSTOM_DIRECT_DELAYED_NAME,
                "x-delayed-message",
                true, false,
                new HashMap<String, Object>(1) {{
                    put("x-delayed-type", "direct");
                }});
    }

    @Bean
    public Queue paymentDelayedQueue() {
        return new Queue(MqConstants.QUEUE_PAYMENT_DELAYED_NAME);
    }

    @Bean
    public Binding bindingCustomPayment() {
        return BindingBuilder
                .bind(paymentDelayedQueue())
                .to(exchangeCustomDirectDelayed())
                .with(MqConstants.ROUTING_KEY_DELAY_PAYMENT_DLK)
                .noargs();
    }
}
