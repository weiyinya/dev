package com.wy.rabbitmqspring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @Author wy
 * @Date 2019/2/13
 */
@Order(1)
@Component
public class AppLiveTime implements CommandLineRunner {

    @Value("${app.duration:0}")
    private int duration;

    /**
     * ApplicationContext的子接口
     */
    @Autowired
    private ConfigurableApplicationContext ctx;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Ready ... running for " + duration + "ms");
        Thread.sleep(duration);
        ctx.close();
    }
}
