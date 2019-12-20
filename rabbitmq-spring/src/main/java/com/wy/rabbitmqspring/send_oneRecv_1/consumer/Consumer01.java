package com.wy.rabbitmqspring.send_oneRecv_1.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

/**
 * @Author wy
 * @Date 2019/2/13
 */
@RabbitListener(queues = "queue1")
public class Consumer01 {

    @RabbitHandler
    public void receive(String message){
        System.out.println("receive: " + message);
    }
}
