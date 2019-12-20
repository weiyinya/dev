package com.wy.rabbitmq.topic_5;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.util.Scanner;

/**
 * 生产者
 * topic模式
 *  根据route_key直接匹配
 *      * 只能替换一个单词
 *      # 可以替换多个单词 (可以包含.)
 * @Author wy
 * @Date 2019/2/9
 */
public class EmitLogTopic {
    private static final String EXCHANGE_NAME = "topic_logs";

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
            Channel channel = connection.createChannel()) {
            channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.TOPIC);

            while (true){
                Scanner scanner = new Scanner(System.in);
                String message = scanner.nextLine();
                String severity = getSeverity(message);
                channel.basicPublish(EXCHANGE_NAME, severity, null, message.getBytes("UTF-8"));
                System.out.println(" [x] Sent '" + severity + "':'" + message + "'");
            }

        }
    }

    private static String getSeverity(String strings) {
        if (strings.contains("error")){
            return "error";
        }
        return strings;
    }
}
