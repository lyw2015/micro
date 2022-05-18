package com.laiyw.micro.notify.controller;

import cn.hutool.core.lang.UUID;
import com.laiyw.micro.common.controller.BaseController;
import com.laiyw.micro.common.domain.AjaxResult;
import com.laiyw.micro.mq.config.MqConstants;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ProjectName micro
 * @Author Laiyw
 * @CreateTime 2022/5/16 16:24
 * @Description TODO
 */
@RestController
@RequestMapping("/test/sender")
public class SenderTest extends BaseController {

    @Autowired
    private RabbitTemplate template;

    /**
     * 默认交换器：Direct
     * routingKey为队列名称
     *
     * @param queueName
     * @return
     */
    @GetMapping("/noex/{queueName}")
    public AjaxResult noex(@PathVariable("queueName") String queueName) {
        Object msg = String.format("使用默认交换器Direct，发送到队列：%s", queueName);
        template.convertAndSend(queueName, msg, new CorrelationData(UUID.fastUUID().toString()));
        return AjaxResult.success(msg);
    }

    /**
     * 发送到所有与fanout交换器绑定的队列
     * 忽略routingKey
     *
     * @return
     */
    @GetMapping("/fanout")
    public AjaxResult fanout() {
        template.convertAndSend(MqConstants.EXCHANGE_FANOUT_NAME, null, "这是Fanout交换器的广播消息", new CorrelationData(UUID.fastUUID().toString()));
        return success();
    }

    @GetMapping("/direct/{routingKey}")
    public AjaxResult direct(@PathVariable("routingKey") String routingKey) {
        String msg = String.format("使用Direct交换器发送到绑定key为%s的队列上", routingKey);
        template.convertAndSend(MqConstants.EXCHANGE_DIRECT_NAME, routingKey, msg, new CorrelationData(UUID.fastUUID().toString()));
        return success(msg);
    }

    @GetMapping("/topic/{routingKey}")
    public AjaxResult topic(@PathVariable("routingKey") String routingKey) {
        String msg = String.format("使用Topic交换器发送到绑定key满足%s规则的队列上", routingKey);
        template.convertAndSend(MqConstants.EXCHANGE_TOPIC_NAME, routingKey, msg, new CorrelationData(UUID.fastUUID().toString()));
        return success(msg);
    }
}
