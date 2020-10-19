package com.uvideo.seckill.system.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

public class ObjectUtils {

    private static ObjectMapper objectMapper = new ObjectMapper();

    public static String toJson(Object value){
        if(value == null){
            return null;
        }else{
            try {
                return objectMapper.writeValueAsString(value);
            } catch (JsonProcessingException e) {
               throw new RuntimeException("对象转成Json格式失败");
            }
        }
    }

    public static Map<String,String> toMap(Object value){
        // 使用反射获取
        /*if(value == null){
            return null;
        }else{
            try {
                return objectMapper.readValue(toJson(value),Map.class);
            } catch (JsonProcessingException e) {
                throw new RuntimeException("对象转成Map格式失败");
            }
        }*/
        return null;
    }


    public static Object toObject(String value,Class clazz){
        if(value == null || value.length() == 0){
            return null;
        }else{
            try {
                return objectMapper.readValue(value,clazz);
            } catch (JsonProcessingException e) {
                throw new RuntimeException("Json格式转对象失败");
            }
        }
    }
}
