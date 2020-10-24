package com.uvideo.seckill.system.controller;

import com.uvideo.seckill.system.seckill.good.view.GoodView;
import com.uvideo.seckill.system.service.seckill.service.SeckillEventService;
import com.uvideo.seckill.system.utils.LoginUtils;
import com.uvideo.seckill.system.utils.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

import static com.uvideo.seckill.system.config.PageConfig.GOODS_HTML;
import static com.uvideo.seckill.system.config.PageConfig.LOGIN_HTML;

/**
 * 商品列表
 *
 * @author wencai.xu
 */
@Controller
public class GoodsController {

    private final SeckillEventService seckillEventService;

    @Autowired
    public GoodsController(SeckillEventService seckillEventService) {
        this.seckillEventService = seckillEventService;
    }

    @RequestMapping(value = "goods_detail_url/{id}")
    private ModelAndView getOneGoodsView(HttpServletRequest request, @PathVariable(value = "id") Long id) {
        String loginStatus = LoginUtils.getSessionUsername(request);
        ModelAndView mv = new ModelAndView();
        if (LoginUtils.isLogin(loginStatus)) {
            mv.setViewName(LOGIN_HTML);
            return mv;
        }
        GoodView goodsView = seckillEventService.getOneGoodsView(id);
        mv.setViewName(GOODS_HTML);
        mv.addObject("activity", goodsView);
        mv.addObject("timer", TimeUtils.getSeckillRemainSeconds(goodsView));
        return mv;
    }


    @RequestMapping(value = {"/goods_detail_url"})
    private String goodsListUri (HttpServletRequest request) {
        String loginStatus = LoginUtils.getSessionUsername(request);
        if (LoginUtils.isLogin(loginStatus)) {
            return LOGIN_HTML;
        }
        return GOODS_HTML;
    }

}
