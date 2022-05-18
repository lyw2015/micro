package com.laiyw.micro.mq.utils;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * @ProjectName micro
 * @Author Laiyw
 * @CreateTime 2022/5/18 14:41
 * @Description TODO
 */

@Slf4j
public class RabbitUtils extends org.springframework.amqp.rabbit.connection.RabbitUtils {

    /**
     * 确认消息
     *
     * @param channel     信道
     * @param deliveryTag 交付标签(消息ID，从1递增)
     * @param multiple    false:仅确认当前交付标签；true:批量确认所有消息(消息ID小于等于自身的ID)
     */
    public static void ack(Channel channel, long deliveryTag, boolean multiple) {
        try {
            channel.basicAck(deliveryTag, multiple);
        } catch (IOException e) {
            log.error("确认消息异常，连接：{}，信道编号：{}，消息ID：{}", channel.getConnection().getClientProvidedName(), channel.getChannelNumber(), deliveryTag);
            e.printStackTrace();
        }
    }

    /**
     * 拒绝消息-nack
     *
     * @param channel     信道
     * @param deliveryTag 交付标签(消息ID，从1递增)
     * @param multiple    false:仅确认当前交付标签；true:批量确认所有消息(消息ID小于等于自身的ID)
     * @param request     false:丢弃消息，true:重新排队
     */
    public static void nack(Channel channel, long deliveryTag, boolean multiple, boolean request) {
        try {
            channel.basicNack(deliveryTag, multiple, request);
        } catch (IOException e) {
            log.error("拒绝消息异常，连接：{}，信道编号：{}，消息ID：{}", channel.getConnection().getClientProvidedName(), channel.getChannelNumber(), deliveryTag);
            e.printStackTrace();
        }
    }

    /**
     * 拒绝消息-reject
     *
     * @param channel     信道
     * @param deliveryTag 交付标签(消息ID，从1递增)
     * @param request     false:丢弃消息，true:重新排队
     */
    public static void reject(Channel channel, long deliveryTag, boolean request) {
        try {
            channel.basicReject(deliveryTag, request);
        } catch (IOException e) {
            log.error("拒绝消息异常，连接：{}，信道编号：{}，消息ID：{}", channel.getConnection().getClientProvidedName(), channel.getChannelNumber(), deliveryTag);
            e.printStackTrace();
        }
    }

    /**
     * 确认当前消息
     */
    public static void ackCurrent(Channel channel, long deliveryTag) {
        ack(channel, deliveryTag, false);
    }

    /**
     * 确认当前及前面(ID<=deliveryTag)所有消息
     */
    public static void ackPreAll(Channel channel, long deliveryTag) {
        ack(channel, deliveryTag, true);
    }

    /**
     * 拒绝当前消息
     */
    public static void nackCurrent(Channel channel, long deliveryTag) {
        nack(channel, deliveryTag, false, true);
    }

    /**
     * 拒绝并丢弃当前消息
     */
    public static void nackAndDiscardCurrent(Channel channel, long deliveryTag) {
        nack(channel, deliveryTag, false, false);
    }

    /**
     * 拒绝当前消息
     */
    public static void rejectCurrent(Channel channel, long deliveryTag) {
        reject(channel, deliveryTag, true);
    }

    /**
     * 拒绝并丢弃当前消息
     */
    public static void rejectAndDiscardCurrent(Channel channel, long deliveryTag) {
        reject(channel, deliveryTag, false);
    }
}
