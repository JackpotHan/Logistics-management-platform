package com.jackpotHan.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * create by emmet
 * create at 05/01/2018
 */
@Component
public class RedisHelper {
    private static final Logger LOGGER = LoggerFactory.getLogger(RedisHelper.class);

    @Resource private RedisTemplate redisTemplate;
    @Resource private StringRedisTemplate stringRedisTemplate;

    public boolean set(String key, Object value){
        boolean result = false;
        try {
            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
            operations.set(key, value);
            result = true;
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return result;
    }

    public Object get(String key) {
        Object result;
        ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
        result = operations.get(key);
        return result;
    }

    /**
     * @param key
     * @param value
     * @param expireTime 秒为单位
     */
    public boolean set(String key, Object value, Long expireTime){
        boolean result = false;
        try {
            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
            operations.set(key, value);
            redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
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

    /**
     * 模糊检索
     * @param pattern
     * @return
     */
    public Set keys(String pattern){
        return stringRedisTemplate.keys( pattern);
    }

    /**
     * 模糊删除
     * @param pattern
     */
    public void dels(String pattern){
        Set<String> keys = redisTemplate.keys(pattern);
        redisTemplate.delete(keys);
    }
}
