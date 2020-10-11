package com.uvideo.seckill.system.controller;

import com.uvideo.seckill.system.response.ResponseRs;
import com.uvideo.seckill.system.seckill.user.User;
import com.uvideo.seckill.system.service.seckill.service.ConfigService;
import com.uvideo.seckill.system.service.seckill.service.GoodsService;
import com.uvideo.seckill.system.service.seckill.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author wencai.xu
 */
@RequestMapping("/user")
@Controller
public class UserController {


    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @RequestMapping(value = "/register",method = RequestMethod.POST)
    @ResponseBody
    private ResponseRs register(HttpServletRequest request, User user){
        ResponseRs responseRs = userService.register(user);
        return responseRs;
    }

    @RequestMapping(value = "/login")
    @ResponseBody
    private ResponseRs login(HttpServletRequest request, User user){
        ResponseRs responseRs = userService.login(user,request);
        return responseRs;
    }
}
