package com.uvideo.seckill.system.service.seckill.service;

import antlr.StringUtils;
import com.uvideo.seckill.system.mapper.user.UserMapper;
import com.uvideo.seckill.system.response.ResponseRs;
import com.uvideo.seckill.system.seckill.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Objects;

/**
 * @author wencai.xu
 */
@Service
public class UserService {

    private final UserMapper userMapper;

    @Autowired
    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public List<User> getUser(){
        return userMapper.findAll();
    }

    public ResponseRs login(User user,HttpServletRequest request) {
        ResponseRs responseRs = new ResponseRs();
        User userDb = userMapper.findUserByNickname(user.getNickname());
        if(Objects.nonNull(userDb)){
            String dbPass = userDb.getPassword();
            if(!"".equals(dbPass) && dbPass.equals(user.getPassword())){
                responseRs.setCode(200);
                responseRs.setMsg("登录成功");
                setCookie(request,user.getNickname());
                return responseRs;
            }
        }
        responseRs.setCode(404);
        responseRs.setMsg("用户不存在或密码错误");
        return responseRs;
    }

    private void setCookie(HttpServletRequest request,String nickname){
        HttpSession session = request.getSession();
        session.setAttribute("username",nickname);
    }

    public ResponseRs register(User user) {
        ResponseRs responseRs = new ResponseRs();
        User save = userMapper.save(user);
        if(Objects.nonNull(save)){
            responseRs.setCode(200);
            responseRs.setMsg("注册成功");
            return responseRs;
        }
        responseRs.setCode(500);
        responseRs.setMsg("注册失败");
        return responseRs;
    }
}
