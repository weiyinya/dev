package com.wy.springamqp.configuration;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author wy
 * @Date 2019/2/18
 */
@Configuration
public class TemplateConfig {

    @Autowired
    private ConnectionFactory connectionFactory;

    @Bean
    public RabbitTemplate rabbitTemplate(){
        RabbitTemplate rabbitTemplate = new RabbitTemplate();

        /**
         * 消息发送到exchange时的回调
         */
        rabbitTemplate.setConfirmCallback(
                (correlationData, ack, cause) -> {
                    if (ack){
                        System.out.println("消息已发送到exchange:" + correlationData.getId());
                    } else {
                        System.out.println("消息未发送到exchange:" + correlationData.getId() + "-" + cause);
                    }
                }
        );

        /**
         * 当exchange分发消息失败时会调用该回调,成功不会调用
         */
        rabbitTemplate.setReturnCallback(
                /**
                 * replyCode 状态码
                 * replyText 对应状态描述
                 */
                (message, replyCode, replyText, exchange, routingKey) ->
                    System.out.println("exchange分发消息到队列失败:" + replyCode + "-" + replyText + "-" + exchange + "-" + routingKey)
        );

        rabbitTemplate.setConnectionFactory(connectionFactory);

        //手动确认
        rabbitTemplate.setMandatory(true);

        return rabbitTemplate;
    }
}
