package com.laiyw.micro.notify.consumer;

import com.laiyw.micro.mq.config.MqConstants;
import com.laiyw.micro.mq.utils.RabbitUtils;
import com.laiyw.micro.notify.api.vo.SenderInfo;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

/**
 * @ProjectName micro
 * @Author Laiyw
 * @CreateTime 2022/5/17 15:57
 * @Description 消息通知消费者
 */
@Slf4j
@Component
public class NotifyConsumer {

    /**
     * concurrency = "2"：开启两个线程处理消息，即两个channel
     *
     * @param senderInfo
     * @param channel
     * @param deliveryTag
     */
    @RabbitListener(queues = MqConstants.QUEUE_NOTIFY_NAME, concurrency = "2")
    public void notify(SenderInfo senderInfo, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag) {
        log.info("队列[{}]收到消息：{}", MqConstants.QUEUE_NOTIFY_NAME, senderInfo);
        RabbitUtils.ackCurrent(channel, deliveryTag);
    }

}
