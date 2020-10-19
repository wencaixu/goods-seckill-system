package com.uvideo.seckill.system.utils;

import com.uvideo.seckill.system.seckill.good.view.GoodView;

/**
 * 秒杀相关时间问题
 *
 * @author wencai.xu
 */
public class TimeUtils {

    public static long getSeckillRemainSeconds(GoodView goodView){
        String startTime = goodView.getSeckillGoods().getStartDate();
        String endTime = goodView.getSeckillGoods().getEndDate();
        return DataUtils.getRemainSeconds(startTime,endTime);
    }

}
