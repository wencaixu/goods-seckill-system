package com.uvideo.seckill.system.service.interceptor;

import com.uvideo.seckill.system.service.limiter.annotation.Limiter;
import com.uvideo.seckill.system.service.seckill.service.limit.RateLimiterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class RateLimiterInterceptor implements HandlerInterceptor {

    private Logger rateLogger = LoggerFactory.getLogger(RateLimiterInterceptor.class);

    private final RateLimiterService rateLimiterService;

    @Autowired
    public RateLimiterInterceptor(RateLimiterService rateLimiterService) {
        this.rateLimiterService = rateLimiterService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if(!(handler instanceof HandlerMethod)){
            return true;
        }
        HandlerMethod method = (HandlerMethod) handler;
        Limiter limiter = method.getMethod().getAnnotation(Limiter.class);
        if(limiter == null) return true;
        if(!rateLimiterService.tryAcquireCurrentAvailable()){
            rateLogger.info("限流获取失败");
            return false;
        }
        rateLogger.info("限流获取成功");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

    }
}
