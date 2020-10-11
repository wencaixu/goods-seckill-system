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
    private String goodsId;
    @Column(name = "miaosha_price")
    private String price;
    private String stockCount;
    private String startDate;
    private String endDate;

}
