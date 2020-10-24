package com.uvideo.seckill.system.service.seckill.service;

import com.uvideo.seckill.system.mapper.order.OrderInfoMapper;
import com.uvideo.seckill.system.mapper.order.OrderMapper;
import com.uvideo.seckill.system.seckill.order.Order;
import com.uvideo.seckill.system.seckill.order.OrderInfo;
import com.uvideo.seckill.system.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * 订单
 *
 * @author wencai.xu
 */
@Service
public class OrderService {

    /**
     * 秒杀
     */
    private final OrderMapper orderMapper;

    /**
     * 订单详情
     */
    private final OrderInfoMapper orderInfoMapper;

    @Autowired
    public OrderService(OrderMapper orderMapper, OrderInfoMapper orderInfoMapper) {
        this.orderMapper = orderMapper;
        this.orderInfoMapper = orderInfoMapper;
    }

    /**
     * 建立用户，订单，订单详情之间的id关联
     */
    public Order save(Order order){
        Order save = orderMapper.save(order);
        if(Objects.isNull(save)){
            return null;
        }
        return save;
    }

    /**
     * 异步数据库订单,返回实体用于更新秒杀订单
     */
    public OrderInfo save(OrderInfo orderInfo){
        OrderInfo save = orderInfoMapper.save(orderInfo);
        if(Objects.isNull(save)){
            return null;
        }
        return save;
    }

    /**
     * 查询用户订单信息
     */


}
