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
    private String nickname;
    private String password;
    private String salt;
    private String head;
    private String registerDate;
    private String lastLoginDate;
    private String loginCount;
}
