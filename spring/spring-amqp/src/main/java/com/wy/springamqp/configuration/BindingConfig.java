package com.wy.springamqp.configuration;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * @Author wy
 * @Date 2019/2/20
 */
@Configuration
public class BindingConfig {

    @Resource(name = QueueConfig.QUEUE_03)
    private Queue queue;

    @Resource(name = ExchangeConfig.EXCHANGE_TOPIC_02)
    private TopicExchange exchange;

    @Bean
    public Binding binding01(){
        return BindingBuilder
                .bind(queue)
                .to(exchange)
                .with("com.wy.exchange.overTime.*");
    }

}
