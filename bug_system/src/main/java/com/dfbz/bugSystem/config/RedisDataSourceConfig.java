package com.dfbz.bugSystem.config;

import com.alibaba.fastjson.support.spring.FastJsonRedisSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

import java.time.Duration;

@Configuration
public class RedisDataSourceConfig {
    @Value("${spring.redis.host}")
    private String host;
    @Value("${spring.redis.port}")
    private Integer port;
    @Value("${spring.redis.password}")
    private String password;


    /**
     * 缓存管理器
     * @param redisConnectionFactory
     * @return
     */
    @Bean
    public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory){

        RedisCacheConfiguration redisCacheConfiguration =
                RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofHours(1));
        return RedisCacheManager.builder(RedisCacheWriter.nonLockingRedisCacheWriter(redisConnectionFactory))
                .cacheDefaults(redisCacheConfiguration).build();

    }



    @Bean
    public JedisPoolConfig jedisPoolConfig(){
        return  new JedisPoolConfig();
    }

    @Bean(name = "jedisConnectionFactory")
    public JedisConnectionFactory jedisConnectionFactory(JedisPoolConfig jedisPoolConfig){
        JedisConnectionFactory jedisConnectionFactory =
                new JedisConnectionFactory(jedisPoolConfig);
        jedisConnectionFactory.setPort(port);
        jedisConnectionFactory.setPassword(password);
        jedisConnectionFactory.setHostName(host);
        return jedisConnectionFactory;
    }


    /**
     * redistemplate对象
     *
     * @param
     * @return
     */
    @Bean
    @ConditionalOnMissingBean(name = "redisTemplate")
    public RedisTemplate<Object,Object> redisTemplate(RedisConnectionFactory jedisConnectionFactory){
        RedisTemplate<Object,Object> redisTemplate = new RedisTemplate<>();
        //fastjson序列化
        FastJsonRedisSerializer fastJsonRedisSerializer = new FastJsonRedisSerializer(Object.class);
        //value序列化方式
        redisTemplate.setValueSerializer(fastJsonRedisSerializer);
        redisTemplate.setHashValueSerializer(fastJsonRedisSerializer);
        //key序列化方式
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setEnableTransactionSupport(true);
        redisTemplate.setConnectionFactory(jedisConnectionFactory);

        return redisTemplate;

    }




}
