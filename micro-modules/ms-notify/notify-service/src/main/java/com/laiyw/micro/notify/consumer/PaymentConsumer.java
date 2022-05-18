package com.laiyw.micro.notify.consumer;

import com.laiyw.micro.common.utils.DateUtils;
import com.laiyw.micro.mq.config.MqConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @ProjectName micro
 * @Author Laiyw
 * @CreateTime 2022/5/18 9:34
 * @Description TODO
 */
@Slf4j
@Component
public class PaymentConsumer {

    /**
     * ttl + 死信队列
     * 延时付款死信队列消费者
     *
     * @param msg
     */
    @RabbitListener(queues = MqConstants.QUEUE_PAYMENT_TTL_DEAD_LETTER_NAME)
    public void paymentTtlDeadLetter(String msg) {
        log.info("{}=>收到来自死信队列的消息：{}", DateUtils.formatByTimePattern(new Date()), msg);
    }

    /**
     * rabbitmq_delayed_message_exchange
     * 延时付款消费者
     *
     * @param msg
     */
    @RabbitListener(queues = MqConstants.QUEUE_PAYMENT_DELAYED_NAME)
    public void paymentDelayed(String msg) {
        log.info("{}=>收到来自死信队列的消息：{}", DateUtils.formatByTimePattern(new Date()), msg);
    }
}
