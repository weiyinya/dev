package com.wy.springboot.config;

import com.wy.springboot.bean.TestAutoConfigVo;
import org.springframework.context.annotation.Bean;

/**
 * @author Dwen
 * @date 2021-03-19 10:20
 */
public class TestAutoConfig {
    @Bean
    public TestAutoConfigVo getAutoBean(){
        return new TestAutoConfigVo();
    }
}
