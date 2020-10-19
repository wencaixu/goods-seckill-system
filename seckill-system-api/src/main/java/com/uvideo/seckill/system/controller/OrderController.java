package com.uvideo.seckill.system.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.uvideo.seckill.system.seckill.good.view.GoodView;
import com.uvideo.seckill.system.service.cache.impl.RedisMapCache;
import com.uvideo.seckill.system.service.cache.impl.RedisStringCache;
import com.uvideo.seckill.system.service.seckill.service.OrderService;
import com.uvideo.seckill.system.service.seckill.service.SeckillGoodsService;
import com.uvideo.seckill.system.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * 订单处理
 *
 * @author wencai.xu
 */
@Controller
public class OrderController {

    ObjectMapper om = new ObjectMapper();

    @Autowired
    private OrderService orderService;

    @Autowired
    private SeckillGoodsService seckillGoodsService;

    @Autowired
    private RedisStringCache stringCache;

    @Autowired
    private RedisMapCache redisMapCache;

    @RequestMapping(value = "/testStringCache")
    @ResponseBody
    public GoodView testCache() throws Exception {
        stringCache.set("aaa","1");
        GoodView oneGoodsView = seckillGoodsService.getOneGoodsView(1);
        String s = ObjectUtils.toJson(oneGoodsView);
        stringCache.set(String.valueOf(oneGoodsView.getGoods().getId()),s,50);
        String s1 = stringCache.get(String.valueOf(oneGoodsView.getGoods().getId()));

        HashMap<String,String> value = new HashMap<>();
        // 关于key的设计存储GoodView的方式
        // key field value  Goods和SeckillGoods对象分开存储
        // eg:
        Map map = ObjectUtils.toMap(oneGoodsView.getGoods());
        // map的类型还需要修复
        redisMapCache.hmset("eeee",map);
        return (GoodView) ObjectUtils.toObject(s1,GoodView.class);
    }


}
