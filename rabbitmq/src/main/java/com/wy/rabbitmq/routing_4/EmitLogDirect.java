package com.wy.rabbitmq.routing_4;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.util.Scanner;

/**
 * 生产者
 * direct模式
 *  根据route_key直接匹配
 * @Author wy
 * @Date 2019/2/9
 */
public class EmitLogDirect {
    private static final String EXCHANGE_NAME = "direct_logs";

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
            Channel channel = connection.createChannel()) {
            channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT);

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
