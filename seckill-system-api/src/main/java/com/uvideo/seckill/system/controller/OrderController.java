package com.uvideo.seckill.system.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.uvideo.seckill.system.seckill.good.view.GoodView;
import com.uvideo.seckill.system.service.cache.impl.RedisMapCache;
import com.uvideo.seckill.system.service.cache.impl.RedisStringCache;
import com.uvideo.seckill.system.service.seckill.service.GoodsService;
import com.uvideo.seckill.system.service.seckill.service.OrderService;
import com.uvideo.seckill.system.service.seckill.service.SeckillEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;



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
    private SeckillEventService seckillEventService;

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private RedisStringCache stringCache;

    @Autowired
    private RedisMapCache redisMapCache;

    @RequestMapping(value = "/testStringCache")
    @ResponseBody
    public GoodView testCache() throws Exception {
        //stringCache.set("aaa","1");
        //GoodView oneGoodsView = seckillGoodsService.getOneGoodsView(1);
        //String s = ObjectUtils.toJson(oneGoodsView);
        //stringCache.set(String.valueOf(oneGoodsView.getGoods().getId()),s,50);
        //String s1 = stringCache.get(String.valueOf(oneGoodsView.getGoods().getId()));

        //HashMap<String,String> value = new HashMap<>();
        // 关于key的设计存储GoodView的方式
        // key field value  Goods和SeckillGoods对象分开存储
        // eg:
        //Map map = toMap(oneGoodsView.getGoods());
        // map的类型还需要修复
        //redisMapCache.hmset("eeee",map);
        // (GoodView) ObjectUtils.toObject(s1,GoodView.class);
        return null;
    }

    @RequestMapping(value = "/test")
    @ResponseBody
    public int test() throws Exception {
        //return goodsService.getOne(1L);
        int status = goodsService.updateGoodStockBy(1L);
        return status;
    }

}
