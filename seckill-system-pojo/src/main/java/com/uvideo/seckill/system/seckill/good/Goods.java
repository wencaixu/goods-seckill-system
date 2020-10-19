package com.uvideo.seckill.system.seckill.good;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

/**
 * 商品表
 *
 * @author wencai.xu
 */
@Data
@Entity
@Table(name = "goods")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler"})
public class Goods {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;
    /**
     * 商品名称
     */
    private String goodsName;
    /**
     * 商品标题
     */
    private String goodsTitle;
    /**
     * 商品图片
     */
    private String goodsImg;
    /**
     * 商品详情
     */
    private String goodsDetail;
    /**
     * 商品价格
     */
    private String goodsPrice;
    /**
     * 商品库存限制
     */
    private Long goodsStock;
}
