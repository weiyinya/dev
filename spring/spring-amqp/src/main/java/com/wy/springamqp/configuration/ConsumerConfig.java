package com.wy.springamqp.configuration;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Header;

import java.io.IOException;

/**
 * 消费者
 * @Author wy
 * @Date 2019/2/15
 */
@Configuration
public class ConsumerConfig {

    /**
     *
     * @see Header 可以直接获取message的一个header属性
     *
     * @param message 使用 Message<T> 接收，可以获取消息的body和headers
     */
    @RabbitListener(
        queues = QueueConfig.QUEUE_01
    )
//    @SendTo(ExchangeConfig.EXCHANGE_TOPIC_02 + "/" + "com.wy.topicExchange02.status")
    public void consumer(
            Message<String> message,
            Channel channel
            ) throws IOException {

        Long deliveryTag = (Long) message.getHeaders().get(AmqpHeaders.DELIVERY_TAG);

        try{
            System.out.println("consumer01-" + message.getPayload() + "-" + deliveryTag);
            if (deliveryTag>10){
                channel.basicAck(deliveryTag,  true);
            }
        } catch (Exception e){

            /**
             * 失败重回队列
             */
            channel.basicReject(deliveryTag, true);
        }

    }

    /**
     * 产出死信
     * @param message
     * @param channel
     */
    @RabbitListener(
            bindings = @QueueBinding(
                    exchange = @Exchange(name = ExchangeConfig.EXCHANGE_TOPIC_02, type = ExchangeTypes.TOPIC),
                    key = "com.wy.deadExchange.receive.#",
                    value = @Queue(
                            name = QueueConfig.QUEUE_02,
                            arguments = {
                                    @Argument(name = "x-dead-letter-exchange", value = ExchangeConfig.EXCHANGE_TOPIC_02),
                                    @Argument(name = "x-dead-letter-routing-key", value = "com.wy.deadExchange.dead")
                            }
                    )
            )
    )
    public void consumer3(Message<String> message, Channel channel) throws IOException {
//        IntStream.rangeClosed(0,10).forEach();
        System.out.println("deadLetterSend:" + message.getPayload());
        channel.basicReject((Long) message.getHeaders().get(AmqpHeaders.DELIVERY_TAG), false);
    }


    /**
     * 消费死信
     * @param message
     * @param channel
     */
    @RabbitListener(
            bindings = @QueueBinding(
                    exchange = @Exchange(name = ExchangeConfig.EXCHANGE_TOPIC_02, type = ExchangeTypes.TOPIC),
                    key = {"com.wy.deadExchange.dead"},
                    value = @Queue(QueueConfig.QUEUE_04)
            )
    )
    public void consumer4(Message<String> message, Channel channel) throws IOException {
        Long deliveryTag = (Long) message.getHeaders().get(AmqpHeaders.DELIVERY_TAG);
        System.out.println("deadLetterConsumer:" + message.getPayload());
        channel.basicAck(deliveryTag, false);
    }

}
