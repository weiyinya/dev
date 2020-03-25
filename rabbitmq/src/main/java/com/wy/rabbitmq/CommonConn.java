package com.wy.rabbitmq;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author Dwen
 * @date 2020-03-25 21:39
 */
public class CommonConn {
    //通用信道。共用一个tcp连接
    public static Connection conn = null;

    static {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try {
            conn = factory.newConnection();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }
}
