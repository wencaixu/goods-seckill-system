package com.uvideo.seckill.system.service.seckill.service;

import com.sun.xml.bind.v2.model.core.ID;
import com.uvideo.seckill.system.mapper.good.GoodsMapper;
import com.uvideo.seckill.system.seckill.good.Goods;
import com.uvideo.seckill.system.service.cache.impl.RedisMapCache;
import com.uvideo.seckill.system.service.cache.impl.RedisStringCache;
import com.uvideo.seckill.system.service.filter.BloomFilter;
import com.uvideo.seckill.system.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import redis.clients.jedis.Jedis;

import java.util.Map;
import java.util.Objects;

/**
 * @author wencai.xu
 */
@Service
public class GoodsService {

    private final GoodsMapper goodsMapper;

    @Autowired
    private BloomFilter bloomFilter;

    @Autowired
    private RedisMapCache redisMapCache;

    @Autowired
    public GoodsService(GoodsMapper goodsMapper) {
        this.goodsMapper = goodsMapper;
    }

    public Goods getOne(Long goodId){
        Map<String, String> good = redisMapCache.hgetAll(String.valueOf(goodId));
        if(!CollectionUtils.isEmpty(good)){
            return (Goods) ObjectUtils.toObject(good,Goods.class);
        }
        if (!bloomFilter.filter(goodId)) {
            Goods oneGood = goodsMapper.getOne(goodId);
            if(Objects.isNull(oneGood)){
                return (Goods) bloomFilter.addNull(goodId);
            }
            Map<String, String> goodMap = ObjectUtils.toMap(oneGood);
            redisMapCache.hmset(String.valueOf(goodId),goodMap);
            return oneGood;
        }
        return null;
    }

    public void updateStock(Goods goods){

         goodsMapper.save(goods);
    }
}
