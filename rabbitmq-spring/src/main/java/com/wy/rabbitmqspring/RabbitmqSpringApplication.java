package com.wy.rabbitmqspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class RabbitmqSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(RabbitmqSpringApplication.class, args);
    }

}

