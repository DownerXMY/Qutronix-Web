package com.qutronix.cloud.feynmanserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.BufferedImageHttpMessageConverter;

/**
 * @author zhongqikuan
 * @email zhong.qikuan@163.com
 * @date 2020/11/16 23:36
 */
@Configuration
public class BufferImageConverterConfig {
    @Bean
    public BufferedImageHttpMessageConverter bufferedImageHttpMessageConverter() {
        return new BufferedImageHttpMessageConverter();
    }
}
