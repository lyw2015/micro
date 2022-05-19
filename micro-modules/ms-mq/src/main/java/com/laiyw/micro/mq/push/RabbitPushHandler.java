package com.laiyw.micro.mq.push;

import cn.hutool.core.lang.UUID;
import com.alibaba.fastjson.JSON;
import com.laiyw.micro.common.utils.DateUtils;
import com.laiyw.micro.mq.config.rabbit.MqConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @ProjectName micro
 * @Author Laiyw
 * @CreateTime 2022/5/19 12:03
 * @Description TODO
 */
@Slf4j
@Service
public class RabbitPushHandler implements PushHandler {

    @Autowired
    private RabbitTemplate template;

    @Override
    public void delayPayment(Object order) {
        String correlationId = UUID.fastUUID().toString();
        log.info("于[{}]推送延时扣款消息[{}]到队列：{}", DateUtils.formatByTimePattern(new Date()), correlationId, MqConstants.QUEUE_PAYMENT_TTL_NAME);
        template.convertAndSend(
                MqConstants.QUEUE_PAYMENT_TTL_NAME,
                order,
                new CorrelationData(correlationId)
        );
        // 用于测试，以插件方式实现的延时队列
        delayPaymentPlugin(order);
    }

    @Override
    public void delayPaymentPlugin(Object order) {
        String correlationId = UUID.fastUUID().toString();
        log.info("于[{}]推送延时扣款消息[{}]到队列，exchange：{}，routingKey：{}", DateUtils.formatByTimePattern(new Date()), correlationId, MqConstants.EXCHANGE_CUSTOM_DIRECT_DELAYED_NAME, MqConstants.ROUTING_KEY_DELAY_PAYMENT_DLK);
        Message message = MessageBuilder
                .withBody(JSON.toJSONBytes(order))
                .setHeader("x-delay", 1000 * 10)
                .build();
        template.convertAndSend(
                MqConstants.EXCHANGE_CUSTOM_DIRECT_DELAYED_NAME,
                MqConstants.ROUTING_KEY_DELAY_PAYMENT_DLK,
                message,
                new CorrelationData(UUID.fastUUID().toString())
        );
    }
}
