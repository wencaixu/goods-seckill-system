package com.uvideo.seckill.system.service.cache;

import java.util.Map;

public interface MapCache {

    /**
     * Good信息存储在Map中
     */
    void hmset(String key,Map<String,String> values);

    /**
     * 库存处理
     */
    Long hincrby(String key,String field,long increment);

    /**
     * 商品信息实体
     */
    Map<String,String> hgetAll(String key);

    /**
     * 获取某个域
     */
    String hget(String key, String field);
}
