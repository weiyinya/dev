package com.wy.springcloudstream.controller;

import com.wy.springcloudstream.stream.StreamClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

/**
 * @Author wy
 * @Date 2019/2/21
 */
@RequestMapping("/cloudStream")
@RestController
public class ProductController {

    @Autowired
    private StreamClient client;

    @GetMapping("/product")
    public Object product01(){

        IntStream.rangeClosed(1,10).forEach(
                code -> client.output().send(
                        MessageBuilder.withPayload("hello:" + code).build())
        );

        Map<String, Object> result = new HashMap<>();
        result.put("code", "200");
        result.put("msg", "success");
        return result;
    }
}
