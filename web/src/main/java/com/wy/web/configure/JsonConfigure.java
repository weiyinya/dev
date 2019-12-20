package com.wy.web.configure;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;

import java.util.ArrayList;
import java.util.List;

/**
 * 使用FastJson进行Json转换
 * @Author wy
 * @Date 2018/10/29 00:56
 */
@Configuration
public class JsonConfigure {
    @Bean
    public HttpMessageConverters configureMessageConverters() {
        // 1.构建了一个HttpMessageConverter FastJson消息转换器
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
        // 2.定义一个配置，设置编码方式，和格式化的形式
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        // 3.设置PrettyFormat格式化，设置空字符，空集合返回空数组[]等.
        fastJsonConfig.setSerializerFeatures(
                // 是否格式化返回Json
               SerializerFeature.PrettyFormat,
               SerializerFeature.WriteMapNullValue,
               SerializerFeature.WriteNullListAsEmpty,
               SerializerFeature.WriteNullNumberAsZero,
               SerializerFeature.WriteNullStringAsEmpty);
        // 4.处理中文乱码问题
        fastJsonConfig.setSerializerFeatures();
        List<MediaType> fastMediaTypes = new ArrayList<>();
        fastMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
        fastConverter.setSupportedMediaTypes(fastMediaTypes);
        // 5.将fastJsonConfig加到消息转换器中
        fastConverter.setFastJsonConfig(fastJsonConfig);
        return new HttpMessageConverters(fastConverter);
    }
}