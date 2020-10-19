package com.uvideo.seckill.system.seckill.good.view;

import com.uvideo.seckill.system.seckill.good.Goods;
import com.uvideo.seckill.system.seckill.good.SeckillGoods;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class GoodView {
    private Goods goods;
    private SeckillGoods seckillGoods;

    public GoodView() {
    }

    public GoodView(Goods goods) {
        this.goods = goods;
    }

    public GoodView(SeckillGoods seckillGoods) {
        this.seckillGoods = seckillGoods;
    }

    public GoodView(Goods goods, SeckillGoods seckillGoods) {
        this.goods = goods;
        this.seckillGoods = seckillGoods;
    }
}
