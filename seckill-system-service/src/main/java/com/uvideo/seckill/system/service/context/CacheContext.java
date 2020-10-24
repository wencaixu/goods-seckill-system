package com.uvideo.seckill.system.service.context;


import com.uvideo.seckill.system.service.cache.config.RedisCacheConfig;
import com.uvideo.seckill.system.service.filter.BloomBloomFilter;
import com.uvideo.seckill.system.service.filter.BloomFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.Jedis;

/**
 * Jedis初始化,需要修改为集群模式
 *
 * @author wencai.xu
 */
@Configuration
public class CacheContext {

    @Autowired
    private RedisCacheConfig redisCacheConfig;

    @Bean
    Jedis createJedis(){
       return new Jedis(redisCacheConfig.getHost(), redisCacheConfig.getPort(), redisCacheConfig.getTimeout());
    }

    @Bean
    BloomFilter createCacheFilter(){
        return new BloomBloomFilter();
    }
}
