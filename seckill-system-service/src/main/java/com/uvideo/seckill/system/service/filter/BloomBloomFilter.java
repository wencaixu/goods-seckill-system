package com.uvideo.seckill.system.service.filter;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.BitSet;

/**
 * 缓存雪崩过滤器
 *
 * @author wencai.xu
 */
public class BloomBloomFilter implements BloomFilter {

    private final int processors = 8;
    /**
     * 预计不超过100万条数据
     */
    private final int threshold = 1000000;
    private BitSet bitSet = new BitSet(threshold);
    private int[] seeds = new int[]{1,3,5,7,9,11,13,15};

    @Override
    public boolean filter(Long id) {
        boolean exclude = false;
        for(int i = 0; i < processors; i++){
            int process = new FilterMethod(i, String.valueOf(id)).process();
            if(bitSet.get(process)){
                exclude = true;
            }
        }
        return exclude;
    }

    @Override
    public Object addNull(Long id) {
        for(int i = 0; i < processors; i++){
            int process = new FilterMethod(i,String.valueOf(id)).process();
            if(!bitSet.get(process)){
                bitSet.set(process);
            }
        }
        return null;
    }

    @Data
    @AllArgsConstructor
    class FilterMethod{
        private int index;
        private String value;
        public int process(){
            int result = 0;
            for(int i = 0; i < value.length(); i++){
                result += value.charAt(i) << 8 + seeds[i%8];
            }
            result += index;
            return result % threshold;
        }
    }
}
