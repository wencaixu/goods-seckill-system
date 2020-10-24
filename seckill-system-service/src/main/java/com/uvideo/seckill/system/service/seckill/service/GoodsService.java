package com.uvideo.seckill.system.service.seckill.service;

import com.uvideo.seckill.system.mapper.good.GoodsMapper;
import com.uvideo.seckill.system.seckill.good.Goods;
import com.uvideo.seckill.system.service.cache.impl.RedisMapCache;
import com.uvideo.seckill.system.service.filter.BloomFilter;
import com.uvideo.seckill.system.utils.MapUtils;
import com.uvideo.seckill.system.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Map;
import java.util.Objects;

/**
 * @author wencai.xu
 */
@Service
public class GoodsService {

    private final GoodsMapper goodsMapper;

    private final BloomFilter bloomFilter;

    private final RedisMapCache redisMapCache;

    @Autowired
    public GoodsService(GoodsMapper goodsMapper, BloomFilter bloomFilter, RedisMapCache redisMapCache) {
        this.goodsMapper = goodsMapper;
        this.bloomFilter = bloomFilter;
        this.redisMapCache = redisMapCache;
    }

    /**
     * 获取单个商品信息
     */
    public Goods getOne(@Param("goodId)") Long goodId) {
        // 布隆过滤
        if (bloomFilter.filter(goodId)) return null;
        // 缓存命中
        Map<String, String> good = redisMapCache.hgetAll(String.valueOf(goodId));
        // 如果命中直接返回
        if (!CollectionUtils.isEmpty(good)) {
            return getGoods(good);
        }
        GoodsCached goodsCached = new GoodsCached(goodId).invoke();
        if (goodsCached.is()) return (Goods) bloomFilter.addNull(goodId);
        Goods oneGood = goodsCached.getOneGood();
        return oneGood;
    }

    /**
     * 异步更新数据库缓存
     */
    public int updateGoodStockBy(@Param("goodsId") Long goodsId) throws Exception {
        // 更新数据库库存数量是异步处理，此处只需要根据id更新处理即可
        int status = goodsMapper.updateStockBy(goodsId);
        if (status != 0) {
            throw new Exception("更新库存失败");
        }
        return status;
    }

    /**
     * 是否超卖
     */
    public boolean isOversold(@Param("goodsId") Long goodsId) {
        // 是否超卖，根据此状态判断
        int stock;
        String stockString = redisMapCache.hget(String.valueOf(goodsId), "goodsStock");
        if (!StringUtils.isEmpty(stockString)) {
            stock = Integer.parseInt(stockString);
        } else {
            stock = goodsMapper.findGoodStockBy(goodsId);
            GoodsCached goodsCached = new GoodsCached(goodsId).invoke();
            // 设置布隆过滤器，不取值
            if (goodsCached.is()) bloomFilter.addNull(goodsId);
        }
        return stock <= 0;
    }

    private Map<String, String> getObjectMap(Goods oneGood) {
        return MapUtils.valueToString(ObjectUtils.objectToMap(oneGood));
    }

    private Goods getGoods(Map<String, String> good) {
        return (Goods) ObjectUtils.mapToObject(MapUtils.valueToObject(good), Goods.class);
    }

    private class GoodsCached {
        private boolean cached;
        private Long goodId;
        private Goods oneGood;

        public GoodsCached(Long goodId) {
            this.goodId = goodId;
        }

        boolean is() {
            return cached;
        }

        public Goods getOneGood() {
            return oneGood;
        }

        public GoodsCached invoke() {
            // 否则查询数据库
            oneGood = goodsMapper.getOne(goodId);
            // 如果未空，则未命中，设置布隆过滤器
            if (Objects.isNull(oneGood)) {
                cached = true;
                return this;
            }
            // 插入到缓存中
            redisMapCache.hmset(Long.toString(goodId), getObjectMap(oneGood));
            cached = false;
            return this;
        }
    }
}
