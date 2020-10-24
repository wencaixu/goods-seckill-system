package com.uvideo.seckill.system.controller;

import com.uvideo.seckill.system.seckill.good.view.GoodView;
import com.uvideo.seckill.system.seckill.order.Order;
import com.uvideo.seckill.system.seckill.order.OrderInfo;
import com.uvideo.seckill.system.service.seckill.service.GoodsService;
import com.uvideo.seckill.system.service.seckill.service.SeckillEventService;
import com.uvideo.seckill.system.utils.LoginUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 秒杀接口
 *
 * @author wencai.xu
 */
@Controller
public class SeckillController {

    private final SeckillEventService seckillEventService;

    private final GoodsService goodsService;

    @Autowired
    public SeckillController(SeckillEventService seckillEventService, GoodsService goodsService) {
        this.seckillEventService = seckillEventService;
        this.goodsService = goodsService;
    }

    @RequestMapping("/seckill/{goodId}")
    @ResponseBody
    public String doSeckill(HttpServletRequest request, @PathVariable("goodId") Long goodId){
        // 1. 判断用户是否登录-压力测试的时候去掉
        if(!LoginUtils.isLogin(request)){
            return "/user/login";
        }
        // 3. 判断goodId的库存-秒杀
        GoodView oneGoodsView = seckillEventService.getOneGoodsView(goodId);
        Long stockCount = oneGoodsView.getSeckillGoods().getStockCount();
        if(stockCount == 0){
            return "{\"status\":\"库存不足\"}";
        }
        oneGoodsView.getSeckillGoods().setStockCount(--stockCount);
        // 4. goodId库存-1（包括秒杀和商品库）
        Long goodStock = oneGoodsView.getGoods().getGoodsStock();
        oneGoodsView.getGoods().setGoodsStock(--goodStock);

        goodsService.isOversold(goodId);
        seckillEventService.updateStock(oneGoodsView.getSeckillGoods());

        // 5. 写入订单信息
           // 构建订单信息
        OrderInfo orderInfo = new OrderInfo();

        Order order = new Order();
        order.setUserId((String) request.getSession().getAttribute("username"));
        order.setGoodsId(goodId);
        order.setOrderId(orderInfo.getId());

            // 保存到数据库

        // 6. 修改用户登录次数

        // 7. 同时修改order_info
        // 8. 修改数据库添加version字段设置乐观锁
        // 9. 返回秒杀成功与否


        // 整体数据需要设置到redis中，并且订单添加放入到对列里和商品库存需要设置锁
        return "{\"status\":\"success\"}";
    }

}
