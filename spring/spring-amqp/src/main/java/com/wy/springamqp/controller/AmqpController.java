package com.wy.springamqp.controller;

import com.wy.springamqp.configuration.ExchangeConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @Author wy
 * @Date 2019/2/15
 */
@RestController
@RequestMapping("/amqp")
public class AmqpController {

    public static int count = 0;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 正常生产消费
     * @param exchange
     * @param topicKey
     * @return
     * @throws InterruptedException
     */
    @GetMapping("/product01/{exchange}/{topicKey}")
    public Object product01(@PathVariable String exchange, @PathVariable String topicKey) throws InterruptedException {

        String message = "" + ++count;

        /**
         * @see CorrelationData 用于唯一标识一次exchange请求
         */
        rabbitTemplate.convertAndSend(
                exchange,
                topicKey,
                message,
                message1 -> {
                    message1.getMessageProperties().setHeader("order_type", "aaa");
                    return message1;
                },
                new CorrelationData(UUID.randomUUID().toString())
        );

        System.out.println("product-" + message);

        Map<String,String> map = new HashMap<>();
        map.put("code", "200");
        map.put("msg", "success");
        return map;
    }

    /**
     * 消费失败，重试后进入死信队列，然后被消费
     */
    @GetMapping("/product02/")
    public Object product02(){
        String message = "" + ++count;

        /**
         * @see CorrelationData 用于唯一标识一次exchange请求
         */
        rabbitTemplate.convertAndSend(
                ExchangeConfig.EXCHANGE_TOPIC_02,
                "com.wy.deadExchange.receive." + count,
                message,
                new CorrelationData(UUID.randomUUID().toString())
        );
        System.out.println("product-" + message);

        Map<String,String> map = new HashMap<>();
        map.put("code", "200");
        map.put("msg", "success");
        return map;
    }


    /**
     * 延时消费
     */
    @GetMapping("/product03/")
    public Object product03(){
        String message = "" + ++count;

        /**
         * @see CorrelationData 用于唯一标识一次exchange请求
         */
        rabbitTemplate.convertAndSend(
                ExchangeConfig.EXCHANGE_TOPIC_02,
                "com.wy.exchange.overTime." + count,
                message,
                new CorrelationData(UUID.randomUUID().toString())
        );
        System.out.println("product-" + message);

        Map<String,String> map = new HashMap<>();
        map.put("code", "200");
        map.put("msg", "success");
        return map;
    }

}
