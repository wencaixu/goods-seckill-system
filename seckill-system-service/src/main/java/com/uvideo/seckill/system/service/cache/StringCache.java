package com.uvideo.seckill.system.service.cache;

/**
 * 字符串缓存
 *
 * @author wencai.xu
 */
public interface StringCache {

    void set(String key,String value) throws Exception;

    void set(String key,String value,int expireSeconds) throws Exception;

    void expire(String key,int expireSeconds) throws Exception;

    long incr(String key) throws Exception;

    long decr(String key) throws Exception;
}
