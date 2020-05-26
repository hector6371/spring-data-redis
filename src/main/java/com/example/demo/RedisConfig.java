package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.hash.DecoratingStringHashMapper;
import org.springframework.data.redis.hash.HashMapper;
import org.springframework.data.redis.hash.Jackson2HashMapper;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@Configuration
@EnableRedisRepositories
public class RedisConfig {


    //It picks everything from spring boot properties
    //@Bean
    //public JedisConnectionFactory connectionFactory() {
    //RedisStandaloneConfiguration config = new RedisStandaloneConfiguration("server", 6379);
    //    return new JedisConnectionFactory();
    //}

    //    @Bean
//    public RedisTemplate<?, ?> redisTemplate() {
//
//        RedisTemplate<byte[], byte[]> template = new RedisTemplate<byte[], byte[]>();
//        return template;
//    }

    @Bean
    public HashMapper<System, String, String> jacksonHashMapper() {
        return new DecoratingStringHashMapper(new Jackson2HashMapper(true));
    }

}

