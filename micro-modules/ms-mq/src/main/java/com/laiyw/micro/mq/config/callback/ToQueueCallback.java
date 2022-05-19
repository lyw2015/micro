package com.laiyw.micro.mq.config.callback;

import com.alibaba.fastjson.JSON;
import com.laiyw.micro.mq.config.MqConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ReturnedMessage;
import org.springframework.amqp.rabbit.connection.PublisherCallbackChannel;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

/**
 * @ProjectName micro
 * @Author Laiyw
 * @CreateTime 2022/5/18 16:56
 * @Description exchange投递到queue异常回调
 * publisher-returns: true
 */

@Slf4j
public class ToQueueCallback implements RabbitTemplate.ReturnsCallback {

    @Override
    public void returnedMessage(ReturnedMessage rm) {
        // 当使用延时插件delayed时跳过，因为信息并没有马上通过exchange发送到queue，而是在exchange暂存相应延时时间
        if (rm.getExchange().equals(MqConstants.EXCHANGE_CUSTOM_DIRECT_DELAYED_NAME)) {
            return;
        }
        String correlationId = rm.getMessage().getMessageProperties().getHeader(PublisherCallbackChannel.RETURNED_MESSAGE_CORRELATION_KEY);
        log.error("消息发布到queue失败：{}", correlationId);
        log.error(JSON.toJSONString(rm.getMessage().getMessageProperties()));
        log.error("交换器：{}，路由键：{}，应答码：{}，原因：{}", rm.getExchange(), rm.getRoutingKey(), rm.getReplyCode(), rm.getReplyText());
        // TODO
    }
}
