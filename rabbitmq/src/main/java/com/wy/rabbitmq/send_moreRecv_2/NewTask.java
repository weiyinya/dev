package com.wy.rabbitmq.send_moreRecv_2;


import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConnectionFactory;
import com.wy.rabbitmq.CommonConn;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

/**
 * 生产者
 * @Author wy
 * @Date 2019/2/9
 */
public class NewTask {
    private final static String QUEUE_NAME = "hello";

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");

        try (Channel channel = CommonConn.conn.createChannel()) {

            //******队列消息不持久化版本******
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);

            while (true){
                //读取控制台输入
                Scanner sc = new Scanner(System.in);
                String message = sc.nextLine();

                channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
                System.out.println(" [x] Sent '" + message + "'");
            }
/*
            //*****队列消息持久化版本*****
            boolean durable = true;
            channel.queueDeclare(TEST_QUEUE, durable, false, false, null);

            while (true){
                //读取控制台输入
                Scanner sc = new Scanner(System.in);
                String message = sc.nextLine();

                channel.basicPublish("", TEST_QUEUE,
                        MessageProperties.PERSISTENT_TEXT_PLAIN,
                        message.getBytes());

                System.out.println(" [x] Sent '" + message + "'");
            }
*/

        }

    }

}
