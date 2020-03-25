package com.wy.rabbitmq.routing_4;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;
import com.wy.rabbitmq.CommonConn;

/**
 * 消费者
 *  指定exchange和routingKey
 * @Author wy
 * @Date 2019/2/9
 */
public class ReceiveLogsDirect {
    private static final String EXCHANGE_NAME = "direct_logs";

    public static void main(String[] argv) throws Exception {
        Channel channel = CommonConn.conn.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, "direct");
        String queueName = channel.queueDeclare().getQueue();

//        channel.queueBind(queueName, EXCHANGE_NAME, "info");
//        channel.queueBind(queueName, EXCHANGE_NAME, "debug");
        channel.queueBind(queueName, EXCHANGE_NAME, "error");

        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println(" [x] Received '" +
                    delivery.getEnvelope().getRoutingKey() + "':'" + message + "'");
        };
        channel.basicConsume(queueName, true, deliverCallback, consumerTag -> { });
    }
}
