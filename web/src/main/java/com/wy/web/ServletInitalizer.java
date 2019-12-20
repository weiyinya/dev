package com.wy.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * @Author wy
 * @Date 2018/10/26
 */
@SpringBootApplication
@EnableConfigurationProperties
public class ServletInitalizer extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(ServletInitalizer.class);
    }
}