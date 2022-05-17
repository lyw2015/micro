package com.laiyw.micro.notify.consumer;

import com.laiyw.micro.mq.config.MqConstants;
import com.laiyw.micro.notify.api.vo.SenderInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
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

    @RabbitListener(queues = MqConstants.QUEUE_NOTIFY_NAME)
    public void notify(SenderInfo senderInfo) {
        log.info("队列[{}]收到消息：{}", MqConstants.QUEUE_NOTIFY_NAME, senderInfo);
    }

}
