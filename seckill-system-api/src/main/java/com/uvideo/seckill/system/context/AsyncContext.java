package com.uvideo.seckill.system.context;

import com.uvideo.seckill.system.async.OverbookAsync;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @author wencai.xu
 */
@Configuration
public class AsyncContext {
    @Bean
    OverbookAsync getOverbookAsync(){
        return new OverbookAsync();
    }
}
