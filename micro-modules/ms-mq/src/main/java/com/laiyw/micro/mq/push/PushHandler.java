package com.laiyw.micro.mq.push;

/**
 * @ProjectName micro
 * @Author Laiyw
 * @CreateTime 2022/5/19 12:02
 * @Description 消息推送接口
 */

public interface PushHandler {

    /**
     * 延时扣款(ttl + 死信队列)
     *
     * @param order 订单数据
     */
    void delayPayment(Object order);

    /**
     * 延时扣款(rabbitmq_delayed_message_exchange)
     *
     * @param order 订单数据
     */
    void delayPaymentPlugin(Object order);
}
