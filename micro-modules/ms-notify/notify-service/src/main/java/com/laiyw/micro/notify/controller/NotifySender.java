package com.laiyw.micro.notify.controller;

import com.alibaba.fastjson.JSON;
import com.laiyw.micro.mq.config.MqConstants;
import com.laiyw.micro.notify.api.client.NotifyClient;
import com.laiyw.micro.notify.api.vo.SenderInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ProjectName micro
 * @Author Laiyw
 * @CreateTime 2022/5/17 16:19
 * @Description TODO
 */
@Slf4j
@RestController
@RequestMapping("/sender")
public class NotifySender implements NotifyClient {

    @Autowired
    private RabbitTemplate template;

    @Override
    public void send(SenderInfo senderInfo) {
        log.info("开始将通知消息发送至消息队列，exchange: {}，routingKey: {}", MqConstants.EXCHANGE_DIRECT_NAME, MqConstants.ROUTING_KEY_NOTIFY);
        log.info(JSON.toJSONString(senderInfo));
        template.convertAndSend(MqConstants.EXCHANGE_DIRECT_NAME, MqConstants.ROUTING_KEY_NOTIFY, senderInfo);
        // 使用默认Direct交换器，routing key为目标队列名称
        // template.convertAndSend(MqConstants.QUEUE_NOTIFY_NAME, senderInfo);
        log.info("消息推送完成");
    }

}
