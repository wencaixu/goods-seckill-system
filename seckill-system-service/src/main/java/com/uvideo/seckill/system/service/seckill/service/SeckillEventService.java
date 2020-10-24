package com.uvideo.seckill.system.service.seckill.service;

import com.uvideo.seckill.system.mapper.good.SeckillGoodsMapper;
import com.uvideo.seckill.system.seckill.good.SeckillGoods;
import com.uvideo.seckill.system.seckill.good.view.GoodView;
import com.uvideo.seckill.system.utils.DataUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author wencai.xu
 */
@Service
public class SeckillEventService {

    private final SeckillGoodsMapper seckillGoodsMapper;

    @Autowired
    public SeckillEventService(SeckillGoodsMapper seckillGoodsMapper) {
        this.seckillGoodsMapper = seckillGoodsMapper;
    }

    public List<SeckillGoods> getSeckillGoods() throws ParseException {
        List<SeckillGoods> seckillGoods = seckillGoodsMapper.findAll();
        List<SeckillGoods> servingEvents = new ArrayList<>();

        for(SeckillGoods goods : seckillGoods){
            if(this.isEventServing(goods)) {
                servingEvents.add(goods);
            }
        }
        return servingEvents;
    }

    /**
     * 秒杀活动是否可投
     */
    private boolean isEventServing(SeckillGoods seckillGoods) throws ParseException {
        return DataUtils.getDataTimeMillis(seckillGoods.getEndDate()) > System.currentTimeMillis();
    }

    public List<GoodView> getGoodsView(){
        return seckillGoodsMapper.findGoodView();
    }

    public void updateStock(SeckillGoods goods){
        seckillGoodsMapper.save(goods);
    }

    public GoodView getOneGoodsView(long id){
        return seckillGoodsMapper.findGoodViewById(id);
    }
}
