package com.uvideo.seckill.system.controller;

import com.uvideo.seckill.system.service.seckill.service.SeckillEventService;
import com.uvideo.seckill.system.config.PageConfig;
import com.uvideo.seckill.system.utils.LoginUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * 页面接口
 *
 * @author wencai.xu
 */
@Controller
public class PageController {

    private final SeckillEventService seckillEventService;

    @Autowired
    public PageController(SeckillEventService seckillEventService) {

        this.seckillEventService = seckillEventService;
    }

    @RequestMapping(value = {"/index"})
    private ModelAndView indexUri(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        LoginUtils.setUserIndexStatus(request, mv);
        mv.setViewName(PageConfig.INDEX_HTML);
        mv.addObject("activity", seckillEventService.getGoodsView());
        return mv;
    }
}



