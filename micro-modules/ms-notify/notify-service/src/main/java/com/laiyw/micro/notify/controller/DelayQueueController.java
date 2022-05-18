package com.laiyw.micro.notify.controller;

import com.laiyw.micro.common.controller.BaseController;
import com.laiyw.micro.common.domain.AjaxResult;
import com.laiyw.micro.common.utils.DateUtils;
import com.laiyw.micro.mq.config.MqConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;
import java.util.Date;

/**
 * @ProjectName micro
 * @Author Laiyw
 * @CreateTime 2022/5/18 9:40
 * @Description TODO
 */
@Slf4j
@RestController
@RequestMapping("/test/sender/delay")
public class DelayQueueController extends BaseController {

    @Autowired
    private RabbitTemplate template;

    @GetMapping("/ttl")
    public AjaxResult senderTtl() {
        log.info("[{}] ttl-发送消息到队列：{}", DateUtils.formatByTimePattern(new Date()), MqConstants.QUEUE_PAYMENT_TTL_NAME);
        template.convertAndSend(MqConstants.QUEUE_PAYMENT_TTL_NAME, "TTL消息");
        log.info("ttl-消息发送完成");
        return success();
    }

    @GetMapping("/delayed")
    public AjaxResult senderDelayed() {
        log.info("[{}] delayed-开始发送延时消息到队列，exchange：{}，routingKey：{}", DateUtils.formatByTimePattern(new Date()), MqConstants.EXCHANGE_CUSTOM_DIRECT_DELAYED_NAME, MqConstants.ROUTING_KEY_DELAY_PAYMENT_DLK);
        Message message = MessageBuilder
                .withBody("这是通过CustomExchange交换器发送的延时消息".getBytes(StandardCharsets.UTF_8))
                //.setContentType(MessageProperties.CONTENT_TYPE_TEXT_PLAIN)
                .setHeader("x-delay", 1000 * 10)
                .build();
        template.convertAndSend(MqConstants.EXCHANGE_CUSTOM_DIRECT_DELAYED_NAME, MqConstants.ROUTING_KEY_DELAY_PAYMENT_DLK, message);
        log.info("delayed-延时消息发送完成");
        return success();
    }
}
