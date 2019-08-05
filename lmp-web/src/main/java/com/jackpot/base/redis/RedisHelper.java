package com.jackpot.base.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author: emmet
 * @date: 2019/1/22
 * Redis工具类
 */
@Slf4j
@Component
public class RedisHelper {

    @Autowired
    private StringRedisTemplate           stringRedisTemplate;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 获取redis配置
     */
    public String getString(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }

    /**
     * 获取redis配置
     */
    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * value++
     */
    public Long incr(String key) {
        return redisTemplate.opsForValue().increment(key);
    }

    /**
     * value--
     */
    public Long decr(String key) {
        return redisTemplate.opsForValue().decrement(key);
    }

    /**
     * 添加key-value
    */
    public void set(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     * 添加key-value
     * @param key
     * @param value
     * @param seconds 单位：秒
    */
    public void set(String key, Object value, long seconds) {
        redisTemplate.opsForValue().set(key, value, seconds, TimeUnit.SECONDS);
    }

    /**
     * 删除对应的value
     *
     * @param key
     */
    public void del(String key) {
        if (exists(key)) {
            redisTemplate.delete(key);
        }
    }

    /**
     * 判断缓存中是否有对应的value
     *
     * @param key
     * @return
     */
    public boolean exists(final String key) {
        return redisTemplate.hasKey(key);
    }
}
