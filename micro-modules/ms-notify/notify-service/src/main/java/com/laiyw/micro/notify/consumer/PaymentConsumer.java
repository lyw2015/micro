package com.laiyw.micro.notify.consumer;

import com.laiyw.micro.common.utils.DateUtils;
import com.laiyw.micro.mq.config.MqConstants;
import com.laiyw.micro.mq.utils.RabbitUtils;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @ProjectName micro
 * @Author Laiyw
 * @CreateTime 2022/5/18 9:34
 * @Description 延时支付消费者
 */
@Slf4j
@Component
public class PaymentConsumer {

    /**
     * ttl + 死信队列
     * 延时付款死信队列消费者
     */
    @RabbitListener(queues = MqConstants.QUEUE_PAYMENT_TTL_DEAD_LETTER_NAME)
    public void paymentTtlDeadLetter(String msg, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag) {
        try {
            log.info("{}=>收到来自死信队列[{}]的消息：{}", DateUtils.formatByTimePattern(new Date()), MqConstants.QUEUE_PAYMENT_TTL_DEAD_LETTER_NAME, msg);
            log.info("DELIVERY_TAG: {}", deliveryTag);
            RabbitUtils.ackCurrent(channel, deliveryTag);
        } catch (Exception e) {
            log.error("业务处理异常", e);
            RabbitUtils.nackCurrent(channel, deliveryTag);
        }
    }

    /**
     * rabbitmq_delayed_message_exchange
     * 延时付款消费者
     */
    @RabbitListener(queues = MqConstants.QUEUE_PAYMENT_DELAYED_NAME)
    public void paymentDelayed(String msg, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag) {
        try {
            log.info("{}=>收到来自队列[{}]的消息：{}", DateUtils.formatByTimePattern(new Date()), MqConstants.QUEUE_PAYMENT_DELAYED_NAME, msg);
            log.info("DELIVERY_TAG: {}", deliveryTag);
            RabbitUtils.ackCurrent(channel, deliveryTag);
        } catch (Exception e) {
            log.error("业务处理异常", e);
            RabbitUtils.rejectCurrent(channel, deliveryTag);
        }
    }
}
