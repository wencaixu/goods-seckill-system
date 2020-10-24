package com.uvideo.seckill.system.service.seckill.service.limit;

import com.google.common.util.concurrent.RateLimiter;
import org.springframework.stereotype.Service;

@Service
public class RateLimiterService {

    /**
     * RateLimiter不太稳定
     */
    private RateLimiter rateLimiter = RateLimiter.create(1);

    public boolean tryAcquireCurrentAvailable(){
        return rateLimiter.tryAcquire();
    }
}
