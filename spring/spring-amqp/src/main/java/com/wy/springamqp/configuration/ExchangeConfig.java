package com.wy.springamqp.configuration;

import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * exchange配置
 * @Author wy
 * @Date 2019/2/15
 */
@Configuration
public class ExchangeConfig {

    public static final String EXCHANGE_TOPIC_01 = "topicExchange01";
    public static final String EXCHANGE_TOPIC_02 = "topicExchange02";
    public static final String EXCHANGE_TOPIC_03 = "topicExchange03";

    @Bean
    public TopicExchange topicExchange01(){
        return (TopicExchange) ExchangeBuilder
                .topicExchange(EXCHANGE_TOPIC_01)
                .durable(true)
                .build();
    }

    @Bean
    public TopicExchange topicExchange02(){
        return (TopicExchange) ExchangeBuilder
                .topicExchange(EXCHANGE_TOPIC_02)
                .durable(true)
                .build();
    }


}
