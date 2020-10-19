package com.uvideo.seckill.system.service.seckill.service;

import com.uvideo.seckill.system.mapper.order.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 订单
 *
 * @author wencai.xu
 */
@Service
public class OrderService {

    private final OrderMapper orderMapper;

    @Autowired
    public OrderService(OrderMapper orderMapper) {

        this.orderMapper = orderMapper;
    }




}
