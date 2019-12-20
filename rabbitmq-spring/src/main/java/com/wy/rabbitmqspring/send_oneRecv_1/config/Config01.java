package com.wy.rabbitmqspring.send_oneRecv_1.config;

import com.wy.rabbitmqspring.send_oneRecv_1.consumer.Consumer01;
import com.wy.rabbitmqspring.send_oneRecv_1.productor.Product01;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * @Author wy
 * @Date 2019/2/13
 */
@Profile("sendRecvOne")
@Configuration
public class Config01 {

    /**
     * 定义队列
     * @return
     */
    @Bean
    public Queue queue(){
        return new Queue("queue1");
    }

    @Profile("consumer")
    @Bean
    public Consumer01 consumer(){
        return new Consumer01();
    }

    @Profile("product")
    @Bean
    public Product01 product(){
        return new Product01();
    }

}
