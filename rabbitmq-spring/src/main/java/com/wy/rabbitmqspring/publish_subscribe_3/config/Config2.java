package com.wy.rabbitmqspring.publish_subscribe_3.config;

import com.wy.rabbitmqspring.publish_subscribe_3.consumer.Consumer;
import com.wy.rabbitmqspring.publish_subscribe_3.product.Product;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * @Author wy
 * @Date 2019/2/14
 */
@Profile("pubSub")
@Configuration
public class Config2 {

    public static String EXCHANGE_FANOUT_NAME = "spring_fanout_1";

    /**
     * 定义exchange
     * @see DirectExchange
     * @see FanoutExchange
     * @see TopicExchange
     * @return
     */
    @Bean
    public FanoutExchange fanout(){
        return new FanoutExchange(EXCHANGE_FANOUT_NAME);
    }


    @Profile("consumer")
    public static class ConsumerConfig {

        /**
         * 临时队列
         * @see Queue
         * @see AnonymousQueue
         * @return
         */
        @Bean
        public Queue autoDeleteQueue1() {
            return new AnonymousQueue();
        }

        @Bean
        public Queue autoDeleteQueue2() {
            return new AnonymousQueue();
        }

        /**
         * 队列-exchange 绑定关系
         * @param fanout
         * @param autoDeleteQueue1
         * @return
         */
        @Bean
        public Binding binding1(FanoutExchange fanout,
                                Queue autoDeleteQueue1) {
            return BindingBuilder
                    .bind(autoDeleteQueue1)
                    .to(fanout);
        }

        @Bean
        public Binding binding2(FanoutExchange fanout,
                                Queue autoDeleteQueue2) {
            return BindingBuilder.bind(autoDeleteQueue2).to(fanout);
        }


        /**
         * bind-to-with
         * @return
         */
/*
        @Bean
        public Binding binding3(DirectExchange fanout,
                                Queue autoDeleteQueue2) {
            return BindingBuilder
                    .bind(autoDeleteQueue2)
                    .to(fanout)
                    .with("");
        }
*/

        @Bean
        public Consumer receiver() {
            return new Consumer();
        }
    }

    @Profile("product")
    @Bean
    public Product product(){
        return new Product();
    }

}
