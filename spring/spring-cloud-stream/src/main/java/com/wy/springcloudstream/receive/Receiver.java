package com.wy.springcloudstream.receive;

import com.wy.springcloudstream.stream.StreamClient;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

/**
 * 消费者
 * @Author wy
 * @Date 2019/2/21
 */
@EnableBinding(StreamClient.class)
public class Receiver {

    @StreamListener(StreamClient.INPUT)
    public void receive(Object object){
        System.out.println(object);
    }
}
