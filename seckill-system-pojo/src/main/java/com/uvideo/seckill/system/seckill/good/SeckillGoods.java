package com.uvideo.seckill.system.seckill.good;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

/**
 * 秒杀商品表
 *
 * @author wencai.xu
 */

@Data
@Entity
@Table(name = "miaosha_goods")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler"})
public class SeckillGoods {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Version
    private int version;

    /**
     * 商品id
     */
    private String goodsId;
    /**
     * 秒杀价格
     */
    @Column(name = "miaosha_price")
    private String price;
    /**
     * 库存数量
     */
    private Long stockCount;
    /**
     * 开始时间
     */
    private String startDate;

    /**
     * 结束时间
     */
    private String endDate;

}
