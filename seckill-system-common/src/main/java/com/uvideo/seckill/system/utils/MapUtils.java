package com.uvideo.seckill.system.utils;

import java.util.HashMap;
import java.util.Map;

/**
 *
 *
 * @author wencai.xu
 */
public class MapUtils {

    public static Map<String,String> valueToString(Map<?,?> map){
        Map<String,String> mapString = new HashMap<>(map.size());
        for(Map.Entry<?,?> entry : map.entrySet()){
            mapString.put(String.valueOf(entry.getKey()),String.valueOf(entry.getValue()));
        }
        return mapString;
    }

    public static Map<String,Object> valueToObject(Map<?,?> map){
        Map<String,Object> mapObject = new HashMap<>(map.size());
        for(Map.Entry<?,?> entry : map.entrySet()){
            mapObject.put(String.valueOf(entry.getKey()), entry.getValue());
        }
        return mapObject;
    }

    public void objectToMapObject(){

    }

}
