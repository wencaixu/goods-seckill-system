package com.uvideo.seckill.system.service.cache.impl;

import com.uvideo.seckill.system.service.cache.MapCache;
import com.uvideo.seckill.system.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import redis.clients.jedis.Jedis;

import java.util.Map;

/**
 * 商品信息缓存到Map中
 *
 * @author wencai.xu
 */
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
                throw new RuntimeException("hmset [key="+key+",value="+ ObjectUtils.objectToJson(values) +"]",ex);
            }
        }
    }

    @Override
    public Long hincrby(String key, String field, long increment) {
        if(key == null || key.length() == 0){
            throw  new RuntimeException("hincrby [key="+key+",field="+field+"]出现异常，key为空");
        }else if(field == null || field.length() == 0){
            throw  new RuntimeException("hincrby [key="+key+",field="+field+"]出现异常，field为空");
        }else{
            try{
                return mapCache.hincrBy(key,field,increment);
            } catch (Exception ex){
                throw new RuntimeException("增加[key="+key+"]失败");
            }
        }
    }

    @Override
    public Map<String, String> hgetAll(String key) {
        if(key == null || key.length() == 0){
            throw new RuntimeException("hgetall [key="+key+"] 失败");
        }else{
            try{
                return mapCache.hgetAll(key);
            } catch (Exception ex) {
                throw new RuntimeException("获取[key="+key+"]，所有域的值失败",ex);
            }
        }
    }

    @Override
    public String hget(String key,String field){
        if(StringUtils.isEmpty(key) || StringUtils.isEmpty(field)){
            return null;
        } else {
            try{
                return mapCache.hget(key,field);
            } catch (Exception ex){
                throw new RuntimeException("获取[key="+key+"的某一个域[field="+field+"]失败",ex);
            }
        }
    }
}
