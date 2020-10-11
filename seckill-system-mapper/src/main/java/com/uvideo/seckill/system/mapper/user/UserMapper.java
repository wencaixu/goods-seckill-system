package com.uvideo.seckill.system.mapper.user;

import com.uvideo.seckill.system.seckill.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.io.Serializable;


/**
 * 用户操作
 *
 * @author wencai.xu
 */
@Repository
public interface UserMapper extends JpaRepository<User, Long>, JpaSpecificationExecutor<User>, Serializable{

    /**
     * 根据名称查找用户
     */
    User findUserByNickname(String nickName);

}
