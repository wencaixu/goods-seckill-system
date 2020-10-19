package com.uvideo.seckill.system.service.seckill.service;

import com.uvideo.seckill.system.mapper.good.GoodsMapper;
import com.uvideo.seckill.system.seckill.good.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wencai.xu
 */
@Service
public class GoodsService {

    private final GoodsMapper goodsMapper;

    @Autowired
    public GoodsService(GoodsMapper goodsMapper) {
        this.goodsMapper = goodsMapper;
    }

    public List<Goods> getOne(Long goodId){
        return goodsMapper.findAll();
    }

    public void updateStock(Goods goods){
         goodsMapper.save(goods);
    }
}
