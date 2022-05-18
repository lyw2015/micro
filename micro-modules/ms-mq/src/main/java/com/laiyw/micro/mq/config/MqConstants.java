package com.laiyw.micro.mq.config;

/**
 * @ProjectName micro
 * @Author Laiyw
 * @CreateTime 2022/5/16 16:06
 * @Description TODO
 */

public class MqConstants {

    // ---------------routing key---------------

    public static final String ROUTING_KEY_DELAY_PAYMENT_DLK = "delay_payment_dlk";

    public static final String ROUTING_KEY_NOTIFY = "notify";


    // ---------------queue---------------

    public static final String QUEUE_PAYMENT_TTL_DEAD_LETTER_NAME = "payment_ttl_dead_letter";

    public static final String QUEUE_PAYMENT_TTL_NAME = "payment_ttl";

    public static final String QUEUE_PAYMENT_DELAYED_NAME = "payment_delayed";

    public static final String QUEUE_NOTIFY_NAME = "notify";


    // ---------------exchange---------------

    public static final String EXCHANGE_DIRECT_TTL_NAME = "exchange_direct_ttl";

    public static final String EXCHANGE_CUSTOM_DIRECT_DELAYED_NAME = "exchange_custom_direct_delayed";

    public static final String EXCHANGE_FANOUT_NAME = "exchange_fanout";

    public static final String EXCHANGE_DIRECT_NAME = "exchange_direct";

    public static final String EXCHANGE_TOPIC_NAME = "exchange_topic";

    public static final String EXCHANGE_HEADERS_NAME = "exchange_headers";
}
