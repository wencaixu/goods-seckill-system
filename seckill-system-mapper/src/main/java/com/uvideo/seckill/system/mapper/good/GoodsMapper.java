package com.uvideo.seckill.system.mapper.good;

import com.uvideo.seckill.system.seckill.good.Goods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

/**
 * @author wencai.xu
 */
@Repository
public interface GoodsMapper extends JpaRepository<Goods, Long>, JpaSpecificationExecutor<Goods>, Serializable {

    /**
     * 更新库存
     *
     * @param goodId 商品id
     * @return 更新状态
     */
    @Transactional(rollbackFor = Exception.class)
    @Modifying(clearAutomatically = true)
    @Query(value = "update Goods g set g.goodsStock=g.goodsStock-1 where g.id=:goodId and g.goodsStock>0")
    int updateStockBy(@Param("goodId") Long goodId);

    /**
     * 库存数量获取
     * @param goodId
     * @return
     */
    @Query(value = "select g.goodsStock from Goods g where g.id=:goodId")
    int findGoodStockBy(@Param("goodId") Long goodId);

}
