package com.wy.springamqp.configuration;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 队列配置
 * @Author wy
 * @Date 2019/2/15
 */
@Configuration
public class QueueConfig {

    public static final String QUEUE_01 = "queue01";
    public static final String QUEUE_02 = "queue02";
    public static final String QUEUE_03 = "queue03";
    public static final String QUEUE_04 = "queue04";

    /**
     * 普通队列
     * @return
     */
    @Bean
    public Queue queue01() {
        return QueueBuilder
                .durable(QUEUE_01) //可持久化
                .build();
    }

    /**
     * 普通队列（专用于消费死信）
     * @return
     */
    @Bean
    public Queue queue04() {
        return QueueBuilder
                .durable(QUEUE_04) //可持久化
                .build();
    }

    /**
     * 延时队列（缓冲队列）
     * @return
     */
    @Bean
    public Queue queue03() {
        return QueueBuilder
                .durable(QUEUE_03) //可持久化
                .withArgument("x-message-ttl", 10000) //10s后过期
                .withArgument("x-dead-letter-exchange", ExchangeConfig.EXCHANGE_TOPIC_02)
                .withArgument("x-dead-letter-routing-key", "com.wy.deadExchange.dead")
                .build();
    }

}
