package com.ltp.config;

import org.springframework.amqp.support.converter.AbstractJackson2MessageConverter;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AMQPConfig {
    /**
     * @return org.springframework.amqp.support.converter.MessageConverter
     * @Author Ltp
     * @Description 使用Json方式序列化
     * @Date 2019/5/10 13:55
     * @Param []
     **/
    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
