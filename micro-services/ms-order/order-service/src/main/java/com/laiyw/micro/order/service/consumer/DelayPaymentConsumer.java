package com.laiyw.micro.order.service.consumer;

import com.alibaba.fastjson.JSON;
import com.laiyw.micro.common.utils.DateUtils;
import com.laiyw.micro.mq.config.rabbit.MqConstants;
import com.laiyw.micro.mq.utils.RabbitUtils;
import com.laiyw.micro.order.service.domain.Order;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.connection.PublisherCallbackChannel;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @ProjectName micro
 * @Author Laiyw
 * @CreateTime 2022/5/19 12:21
 * @Description TODO
 */
@Slf4j
@Component
public class DelayPaymentConsumer {

    @RabbitListener(queues = MqConstants.QUEUE_PAYMENT_TTL_DEAD_LETTER_NAME)
    public void payment(Order order, Channel channel,
                        @Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag,
                        @Header(PublisherCallbackChannel.RETURNED_MESSAGE_CORRELATION_KEY) String correlationId) {
        try {
            log.info("于[{}]收到来自死信队列[{}]的消息：{}", DateUtils.formatByTimePattern(new Date()), MqConstants.QUEUE_PAYMENT_TTL_DEAD_LETTER_NAME, correlationId);
            log.info("deliveryTag: {}", deliveryTag);
            log.info(JSON.toJSONString(order));
            // TODO:判断订单是否支付，未支付则取消订单、恢复库存，否则不做处理
            RabbitUtils.ackCurrent(channel, deliveryTag);
        } catch (Exception e) {
            log.error("业务处理异常", e);
            RabbitUtils.nackAndDiscardCurrent(channel, deliveryTag);
        }
    }

    @RabbitListener(queues = MqConstants.QUEUE_PAYMENT_DELAYED_NAME)
    public void paymentDelayed(Message message, Channel channel) {
        String correlationId = message.getMessageProperties().getHeader(PublisherCallbackChannel.RETURNED_MESSAGE_CORRELATION_KEY);
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        try {
            Order order = JSON.parseObject(message.getBody(), Order.class);
            log.info("于[{}]收到来队列[{}]的消息：{}", DateUtils.formatByTimePattern(new Date()), MqConstants.QUEUE_PAYMENT_DELAYED_NAME, correlationId);
            log.info("deliveryTag: {}", deliveryTag);
            log.info(JSON.toJSONString(order));
            // TODO:判断订单是否支付，未支付则取消订单、恢复库存，否则不做处理
            RabbitUtils.ackCurrent(channel, deliveryTag);
        } catch (Exception e) {
            log.error("业务处理异常", e);
            RabbitUtils.rejectAndDiscardCurrent(channel, deliveryTag);
        }
    }
}
