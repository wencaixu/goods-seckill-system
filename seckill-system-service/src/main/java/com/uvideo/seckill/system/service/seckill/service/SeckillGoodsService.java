package com.uvideo.seckill.system.service.seckill.service;

import com.uvideo.seckill.system.mapper.good.SeckillGoodsMapper;
import com.uvideo.seckill.system.seckill.good.SeckillGoods;
import com.uvideo.seckill.system.seckill.good.cross.GoodView;
import com.uvideo.seckill.system.utils.DataUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author wencai.xu
 */
@Service
public class SeckillGoodsService{

    private final SeckillGoodsMapper seckillGoodsMapper;

    @Autowired
    public SeckillGoodsService(SeckillGoodsMapper seckillGoodsMapper) {
        this.seckillGoodsMapper = seckillGoodsMapper;
    }

    public List<SeckillGoods> getSeckillGoods(){
        List<SeckillGoods> seckillGoods = seckillGoodsMapper.findAll();
        if(CollectionUtils.isEmpty(seckillGoods)){
            return new ArrayList<>();
        }
        return seckillGoods.stream().filter(x -> {
            try {
                return DataUtils.getDataTimeMillis(x.getEndDate()) > System.currentTimeMillis();
            } catch (ParseException e) {
                e.printStackTrace();
                return false;
            }
        }).collect(Collectors.toList());
    }

    public List<GoodView> getGoodsView(){
        return seckillGoodsMapper.findGoodView();
    }

    public GoodView getOneGoodsView(long id){
        return seckillGoodsMapper.findGoodViewById(id);
    }
}
