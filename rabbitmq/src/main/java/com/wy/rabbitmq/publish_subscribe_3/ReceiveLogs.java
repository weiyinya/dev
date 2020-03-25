package com.wy.rabbitmq.publish_subscribe_3;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;
import com.wy.rabbitmq.CommonConn;

/**
 * 消费者
 * @Author wy
 * @Date 2019/2/9
 */
public class ReceiveLogs {
    private static final String EXCHANGE_NAME = "logs";

    public static void main(String[] argv) throws Exception {
        Channel channel = CommonConn.conn.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, "fanout");

        //生成随机队列（不持久化、独立、消费者退出时自动删除）
        String queueName = channel.queueDeclare().getQueue();
        //exchange、queue绑定
        channel.queueBind(queueName, EXCHANGE_NAME, "");

        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println(" [x] Received '" + message + "'");
        };
        channel.basicConsume(queueName, true, deliverCallback, consumerTag -> { });
    }
}
