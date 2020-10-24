package com.uvideo.seckill.system.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;

/**
 * 字符串对象处理工具集合
 *
 * @author wencai.xu
 */
public class ObjectUtils {

    private static Logger logger = LoggerFactory.getLogger(ObjectUtils.class);

    private static ObjectMapper om = new ObjectMapper();

    public static String objectToJson(Object goods) {
        if (goods == null) {
            return null;
        }
        try {
            return om.writeValueAsString(goods);
        } catch (JsonProcessingException e) {
            logger.info("对象转换成字符串出现异常", e);
            return null;
        }
    }

    public static <T> T stringToObject(String json, Class<T> type) {
        if (json.length() == 0 || "".equals(json.trim())) {
            return null;
        }
        try {
            T t = om.readValue(json, type);
            return t;
        } catch (JsonProcessingException e) {
            logger.info("字符串转对象出现异常", e);
            return null;
        }
    }

    public static Object mapToObject(Map<String, Object> map, Class<?> clazz) {
        if (CollectionUtils.isEmpty(map)) {
            return null;
        }
        Object object = om.convertValue(map, clazz);
        return object;
    }

    public static Map<String, Object> objectToMap(Object object) {
        if (object == null) {
            return null;
        }
        @SuppressWarnings("unchecked")
        Map<String, Object> map = om.convertValue(object, Map.class);
        return map;
    }

    public static <T> List<T> stringToList(String json, Class<T> type) {
        JavaType javaType = om.getTypeFactory().constructParametricType(List.class, type);
        try {
            List<T> list = om.readValue(json, javaType);
            return list;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
