package com.laiyw.micro.notify.controller;

import cn.hutool.core.lang.UUID;
import com.alibaba.fastjson.JSON;
import com.laiyw.micro.mq.config.rabbit.MqConstants;
import com.laiyw.micro.notify.api.client.NotifyClient;
import com.laiyw.micro.notify.api.vo.SenderInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
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
public class NotifyController implements NotifyClient {

    @Autowired
    private RabbitTemplate template;

    @Override
    public void send(SenderInfo senderInfo) {
        log.info("开始将通知消息发送至消息队列，exchange: {}，routingKey: {}", MqConstants.EXCHANGE_DIRECT_NAME, MqConstants.ROUTING_KEY_NOTIFY);
        log.info(JSON.toJSONString(senderInfo));
        template.convertAndSend(MqConstants.EXCHANGE_DIRECT_NAME, MqConstants.ROUTING_KEY_NOTIFY, senderInfo, new CorrelationData(UUID.fastUUID().toString()));
        log.info("消息推送完成");
    }

}
