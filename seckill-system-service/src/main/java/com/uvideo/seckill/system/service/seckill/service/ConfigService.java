package com.uvideo.seckill.system.service.seckill.service;

import com.uvideo.seckill.system.service.cache.config.RedisCacheConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 配置中心
 *
 * @author wencai.xu
 */
@Service
public class ConfigService {

    @Autowired
    private RedisCacheConfig redisCacheConfig;

    public RedisCacheConfig getRedisCacheConfig(){
        return redisCacheConfig;
    }

}
