package com.uvideo.seckill.system.service.filter;

/**
 *
 * @author wencai.xu
 */
public interface BloomFilter {

     boolean filter(Long id);

     Object addNull(Long id);
}
