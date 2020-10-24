package com.uvideo.seckill.system.seckill.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

/**
 * 用户信息表
 *
 * @author wencai.xu
 */
@Data
@Entity
@Table(name = "miaosha_user")
@ToString
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler"})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private String id;

    @Version
    private int version;

    /**
     * 别名
     */
    private String nickname;

    /**
     * 密码
     */
    private String password;

    /**
     * 密码加盐
     */
    private String salt;

    /**
     * 头像
     */
    private String head;

    /**
     * 注册时间
     */
    private String registerDate;

    /**
     * 最后登录时间
     */
    private String lastLoginDate;

    /**
     * 登录次数
     */
    private String loginCount;
}
