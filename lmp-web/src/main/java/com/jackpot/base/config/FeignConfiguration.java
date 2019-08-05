package com.jackpot.base.config;

import com.jackpot.base.feign.logger.LmpFeignLoggerFactory;
import feign.Logger;
import org.springframework.cloud.openfeign.FeignLoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: emmet
 * @date: 2018/12/18
 */
@Configuration
public class FeignConfiguration {
    @Bean
    Logger.Level feignLoggerLevel() {
        //这里记录所有，根据实际情况选择合适的日志level
        return Logger.Level.FULL;
    }

    @Bean
    FeignLoggerFactory infoFeignLoggerFactory() {
        return new LmpFeignLoggerFactory();
    }
}
