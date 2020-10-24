package com.uvideo.seckill.system.config.mvc;

import com.uvideo.seckill.system.service.interceptor.RateLimiterInterceptor;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

@SpringBootApplication
public class LimiterWebMvcConfigurer implements WebMvcConfigurer {

    @Resource
    private RateLimiterInterceptor rateLimiterInterceptor;


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 暂时设置过滤所有接口
        registry.addInterceptor(rateLimiterInterceptor).addPathPatterns("/**");
    }
}
