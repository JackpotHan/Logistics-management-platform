package com.jackpotHan.mq;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    /** 接口日志队列 **/
    @Bean
    public Queue addApiLog() {
        return new Queue(QueueCode.MESSAGE_API_LOG);
    }

}
