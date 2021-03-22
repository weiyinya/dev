package com.wy.springboot.config;

import com.wy.springboot.bean.TestConfigVo;
import org.springframework.context.annotation.Bean;

/**
 * @author Dwen
 * @date 2021-03-19 10:20
 */
public class TestConfig {
    @Bean
    public TestConfigVo getBean() {
        return new TestConfigVo();
    }
}
