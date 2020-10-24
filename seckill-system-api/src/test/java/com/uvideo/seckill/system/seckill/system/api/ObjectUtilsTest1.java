package com.uvideo.seckill.system.seckill.system.api;

import com.uvideo.seckill.system.seckill.good.Goods;
import com.uvideo.seckill.system.utils.ObjectUtils;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Map;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertSame;

public class ObjectUtilsTest1 {


    static Goods goods;

    @BeforeClass
    public static void beforeClass(){
        goods = new Goods();
        goods.setId(1L);
        goods.setGoodsStock(2L);
        goods.setGoodsDetail("aaaa");
        goods.setGoodsImg("http://www.baidu.com");
        goods.setGoodsName("goodName");
        goods.setGoodsPrice(12.3+"");
        goods.setGoodsTitle("title");
    }

    @Test
    public void objectToJson() {
        String jsonString = ObjectUtils.objectToJson(goods);
        assertEquals("{\"id\":1,\"goodsName\":\"goodName\",\"goodsTitle\":\"title\",\"goodsImg\":\"http://www.baidu.com\",\"goodsDetail\":\"aaaa\",\"goodsPrice\":\"12.3\",\"goodsStock\":2}",jsonString);
    }

    @Test
    public void stringToObject() {
        String goodsString = ObjectUtils.objectToJson(goods);
        Goods goodsNew = ObjectUtils.stringToObject(goodsString,Goods.class);
    }

    @Test
    public void mapToObject() {
        Map<String,Object> map = (Map<String, Object>) ObjectUtils.objectToMap(goods);
        for(Map.Entry<String,Object> entry : map.entrySet()){
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }
        Goods goods = (Goods) ObjectUtils.mapToObject(map,Goods.class);
        System.out.println(goods.toString());
    }

    @Test
    public void objectToMap() {

    }

}
