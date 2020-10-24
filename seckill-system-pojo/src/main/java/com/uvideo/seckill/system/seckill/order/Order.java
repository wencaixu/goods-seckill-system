package com.uvideo.seckill.system.seckill.order;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

/**
 * 订单
 *
 * @author wencai.xu
 */

@Data
@Entity
@Table(name = "miaosha_order")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler"})
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Version
    private int version;

    /**
     * 用户id
     */
    private String userId;
    /**
     * 订单id
     */
    private Long orderId;
    /**
     * 商品id
     */
    private Long goodsId;
}
