package com.uvideo.seckill.system.seckill.order.view;

import com.uvideo.seckill.system.seckill.good.Goods;
import com.uvideo.seckill.system.seckill.order.Order;
import com.uvideo.seckill.system.seckill.order.OrderInfo;
import lombok.Data;
import lombok.ToString;

/**
 * 订单视图
 *
 * @author wencai.xu
 */
@Data
@ToString
public class OrderView {

    private Order order;

    private Goods goods;

    private OrderInfo orderInfo;

    public OrderView() {
    }

    public OrderView(Goods goods) {
        this.goods = goods;
    }

    public OrderView(Order order) {
        this.order = order;
    }

    public OrderView(OrderInfo orderInfo) {
        this.orderInfo = orderInfo;
    }

    public OrderView(Order order, OrderInfo orderInfo) {
        this.order = order;
        this.orderInfo = orderInfo;
    }
}
