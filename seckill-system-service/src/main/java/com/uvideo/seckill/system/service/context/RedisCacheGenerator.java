package com.uvideo.seckill.system.service.context;

import com.uvideo.seckill.system.service.cache.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 缓存配置
 *
 * @author wencai.xu
 */
@Configuration
public class RedisCacheGenerator {

    private final RedisCache redisCache;

    @Autowired
    public RedisCacheGenerator(RedisCache redisCache) {
        this.redisCache = redisCache;
    }

    @Bean
    void jedisCacheInitializer(){
        System.out.println(redisCache.getHost());
    }
}
