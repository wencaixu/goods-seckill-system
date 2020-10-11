package com.uvideo.seckill.system.service.cache.impl;

import com.uvideo.seckill.system.service.cache.StringCache;
import redis.clients.jedis.Jedis;


/**
 *
 * @author wencai.xu
 */
public class RedisStringCache implements StringCache {

    private Jedis redisCache;

    public RedisStringCache(Jedis cache) {
        this.redisCache = redisCache;
    }

    @Override
    public void set(String key, String value) throws Exception {
        try{
            redisCache.set(key,value);
        }catch (Exception ex){
            throw new Exception("set [key=" + key + ", value=" + value + "]", ex);
        }
    }

    @Override
    public void set(String key, String value, int expireSeconds) throws Exception {
        try {
            redisCache.setex(key,expireSeconds,value);
        }catch (Exception ex){
            throw new Exception(
                    "set [key=" + key + ", expireSeconds=" + expireSeconds + ", value=" + value + "]", ex);
        }
    }

    @Override
    public void expire(String key, int expireSeconds) throws Exception {
        try {
            redisCache.expire(key,expireSeconds);
        }catch (Exception ex){
            throw  new Exception("set [key="+key+"expire time error",ex);
        }
    }

    @Override
    public long incr(String key) throws Exception {
        long incr = 0L;
        try {
            incr = redisCache.incr(key);
        }catch (Exception ex){
            throw new Exception("incr [key="+key+"]",ex);
        }
        return incr;
    }

    @Override
    public long decr(String key) throws Exception {
        long decr = 0L;
        try{
            decr = redisCache.decr(key);
        } catch (Exception ex){
            throw new Exception("decr [key="+key+"]",ex);
        }
        return decr;
    }
}
