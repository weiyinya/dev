package com.wy.rabbitmq.publish_subscribe_3;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.util.Scanner;

/**
 * 生产者（无需知道队列，直接发送到exchange中）
 * fanout模式：将消息转发到它所知道的队列中（扇出模式）
 *  它没有给我们太大的灵活性 - 它只能进行无意识的广播
 * @Author wy
 * @Date 2019/2/9
 */
public class EmitLog {
    private static final String EXCHANGE_NAME = "logs";

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
            Channel channel = connection.createChannel()) {

            //定义exchange
            channel.exchangeDeclare(EXCHANGE_NAME, "fanout");

            while (true){
                //读取控制台输入
                Scanner sc = new Scanner(System.in);
                String message = sc.nextLine();

                //向exchange发送消息
                channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes("UTF-8"));
                System.out.println(" [x] Sent '" + message + "'");
            }

        }
    }
}
