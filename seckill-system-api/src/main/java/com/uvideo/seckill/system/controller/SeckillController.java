package com.uvideo.seckill.system.controller;

import com.uvideo.seckill.system.service.seckill.service.SeckillGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class SeckillController {

    private final SeckillGoodsService seckillGoodsService;

    @Autowired
    public SeckillController(SeckillGoodsService seckillGoodsService) {
        this.seckillGoodsService = seckillGoodsService;
    }

}
