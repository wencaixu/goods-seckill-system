package com.uvideo.seckill.system.seckill.order;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

/**
 * 订单详情
 *
 * @author wencai.xu
 */

@Data
@Entity
@Table(name = "order_info")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler"})
public class OrderInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Version
    private int version;

    /**
     * 用户唯一标识
     */
    private String userId;

    /**
     * 商品id
     */
    private String goodsId;

    /**
     * 邮寄地址
     */
    private String deliveryAddrId;

    /**
     * 商品名称
     */
    private String goodsName;

    /**
     * 商品数量
     */
    private String goodsCount;

    /**
     * 商品价格
     */
    private String goodsPrice;

    /**
     * 订单通道
     */
    private String orderChannel;

    /**
     * 状态
     */
    private String status;

    /**
     * 创建日期
     */
    private String createDate;

    /**
     * 支付时间
     */
    private String payDate;
}
