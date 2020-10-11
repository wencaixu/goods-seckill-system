package com.uvideo.seckill.system.mapper.good;

import com.uvideo.seckill.system.seckill.good.SeckillGoods;
import com.uvideo.seckill.system.seckill.good.cross.GoodView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 * @author wencai.xu
 */
@Repository
public interface SeckillGoodsMapper extends JpaRepository<SeckillGoods,Long> {

    @Query(value = "SELECT new com.uvideo.seckill.system.seckill.good.cross.GoodView(g,s) FROM SeckillGoods s, com.uvideo.seckill.system.seckill.good.Goods g WHERE g.id = s.goodsId")
    List<GoodView> findGoodView();

    @Query(value = "SELECT new com.uvideo.seckill.system.seckill.good.cross.GoodView(g,s) FROM SeckillGoods s, com.uvideo.seckill.system.seckill.good.Goods g WHERE g.id = s.goodsId AND s.id = ?1")
    GoodView findGoodViewById(long id);

}
