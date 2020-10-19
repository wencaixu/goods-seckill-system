package com.uvideo.seckill.system.service.cache;

import java.util.Map;

public interface MapCache {

    void hmset(String key,Map<String,String> values);

}
