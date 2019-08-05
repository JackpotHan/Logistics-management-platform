package com.jackpot.base.feign.logger;

import feign.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FeignLoggerFactory;

/**
 * @author: emmet
 * @date: 2019/1/25
 */
public class LmpFeignLoggerFactory implements FeignLoggerFactory {
    @Override
    public Logger create(Class<?> type) {
        return new LmpFeignLogger(LoggerFactory.getLogger(type));
    }
}
