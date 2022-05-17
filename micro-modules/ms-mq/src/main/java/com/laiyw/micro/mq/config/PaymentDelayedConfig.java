package com.laiyw.micro.mq.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.nio.charset.StandardCharsets;
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
    public CustomExchange customExchangePaymentDlx() {
        return new CustomExchange(
                MqConstants.EXCHANGE_CUSTOM_DIRECT_DLX_NAME,
                "x-delayed-message",
                true, false,
                new HashMap<String, Object>(1) {{
                    put("x-delayed-type", "direct");
                }});
    }

    @Bean
    public Binding bindingCustomPayment(Queue delayPaymentDlqQueue) {
        return BindingBuilder
                .bind(delayPaymentDlqQueue)
                .to(customExchangePaymentDlx())
                .with(MqConstants.ROUTING_KEY_DELAY_PAYMENT_DLK)
                .noargs();
    }

    public void send() {
        MessageBuilder builder = MessageBuilder.withBody("插件".getBytes(StandardCharsets.UTF_8));
        builder.setHeader("x-delay", 1000 * 10);
    }
}
