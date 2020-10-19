package com.uvideo.seckill.system.service.cache.impl;

import com.uvideo.seckill.system.service.cache.MapCache;
import com.uvideo.seckill.system.utils.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import redis.clients.jedis.Jedis;

import java.util.Map;

@Component
public class RedisMapCache implements MapCache {

    @Autowired
    private Jedis mapCache;

    @Override
    public void hmset(String key,Map<String, String> values) {
        if(CollectionUtils.isEmpty(values)){
            return;
        }else{
            try{
                mapCache.hmset(key,values);
            }catch (Exception ex){
                throw new RuntimeException("hmset [key="+key+",value="+ ObjectUtils.toJson(values) +"]",ex);
            }
        }
    }
}
