package com.uvideo.seckill.system.service.context;

import com.uvideo.seckill.system.service.cache.context.config.RedisCacheConfig;
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

    private final RedisCacheConfig redisCacheConfig;

    @Autowired
    public RedisCacheGenerator(RedisCacheConfig redisCacheConfig) {
        this.redisCacheConfig = redisCacheConfig;
    }

    @Bean
    void jedisCacheInitializer(){
        System.out.println(redisCacheConfig.getHost());
    }
}
