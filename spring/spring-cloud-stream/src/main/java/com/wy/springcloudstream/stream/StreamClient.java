package com.wy.springcloudstream.stream;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

/**
 * 自定义管道
 * @Author wy
 * @Date 2019/2/21
 */
public interface StreamClient {
    String INPUT = "myInput";
    String OUTPUT = "myOutput";

    @Input(StreamClient.INPUT)
    SubscribableChannel input();

    @Output(StreamClient.OUTPUT)
    MessageChannel output();
}
