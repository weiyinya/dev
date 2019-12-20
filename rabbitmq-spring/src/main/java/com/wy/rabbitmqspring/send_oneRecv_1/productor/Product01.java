package com.wy.rabbitmqspring.send_oneRecv_1.productor;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * @Author wy
 * @Date 2019/2/13
 */
public class Product01 {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private Queue queue;

    /**
     * 任务
     *
     */
    @Scheduled(fixedDelay = 1000, initialDelay = 500)
    public void send() {

        String message = "hello world!";

        rabbitTemplate.convertAndSend(queue.getName(), message);

        System.out.println("send message: " + message);
    }
}
