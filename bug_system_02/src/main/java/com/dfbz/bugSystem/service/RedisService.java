package com.dfbz.bugSystem.service;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 用于提供给controller层调用的方法，
 * 实现向缓存数据库中存取数据操作
 */
@Service
public class RedisService {


    @Resource
    RedisTemplate<String,Object> redisTemplate;

    public void set(String key,Object value){
        RedisSerializer redisSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(redisSerializer);
        ValueOperations<String,Object> vo = redisTemplate.opsForValue();

        vo.set(key,value);

    }


    public Object get(String key){
        ValueOperations<String,Object> vo = redisTemplate.opsForValue();
        return vo.get(key);
    }

}
