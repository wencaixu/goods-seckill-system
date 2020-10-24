package com.uvideo.seckill.system.controller;

import com.uvideo.seckill.system.service.limiter.annotation.Limiter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LimiterController {
    @Limiter
    @RequestMapping(value = "/limiter")
    public String rateLimiter(){

        return "123";
    }
}
