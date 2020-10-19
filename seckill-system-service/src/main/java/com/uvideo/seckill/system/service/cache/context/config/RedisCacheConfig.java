package com.uvideo.seckill.system.service.cache.context.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * RedisCache
 *
 * @author wencai.xu
 */
@Data
@Component
@ConfigurationProperties(prefix = "redis")
public class RedisCacheConfig {
    private String host;
    private int port;
    private int timeout;
    private String password;
    private int poolMaxTotal;
    private int poolMaxIdle;
    private int poolMaxWait;
}
