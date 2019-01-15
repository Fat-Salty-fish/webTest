package com.sinoyd.survey.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

///**
// * @Description
// * @auther 李忠杰
// * @create 2019-01-07 14:34
// */
//@Configuration
//@EnableCaching
//public class RedisConfig {
//
//    @Bean
//    public RedisTemplate<String,Object> redisTemplate(RedisConnectionFactory factory){
//        RedisTemplate<String,Object> template = new RedisTemplate<>();
//        template.setConnectionFactory(factory);
//        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
//        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
//        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
//        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);
//        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
//        //key设置序列化方式
//        template.setKeySerializer(stringRedisSerializer);
//        //hash的key也采用String的序列化方式
//        template.setHashKeySerializer(stringRedisSerializer);
//        //value序列化采用jackson的方式
//        template.setValueSerializer(jackson2JsonRedisSerializer);
//        //hash的value序列化方式采用jackson方式
//        template.setHashKeySerializer(jackson2JsonRedisSerializer);
//        template.afterPropertiesSet();
//        return template;
//    }
//
//    @Bean
//    public CacheManager cacheManager(RedisTemplate redisTemplate){
//        RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);
//        //设置缓存过期时间
//        cacheManager.setDefaultExpiration(10000);
//        return cacheManager;
//    }
//
//}
