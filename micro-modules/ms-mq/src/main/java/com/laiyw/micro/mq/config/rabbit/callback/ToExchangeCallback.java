package com.laiyw.micro.mq.config.rabbit.callback;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

/**
 * @ProjectName micro
 * @Author Laiyw
 * @CreateTime 2022/5/18 16:54
 * @Description 投递到exchange回调
 * publisher-confirm-type: correlated
 */
@Slf4j
public class ToExchangeCallback implements RabbitTemplate.ConfirmCallback {

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        if (!ack) {
            log.error("消息发布到exchange异常：{}", correlationData);
            // TODO
            return;
        }
        log.debug("消息发布到exchange成功：{}", correlationData);
    }
}
