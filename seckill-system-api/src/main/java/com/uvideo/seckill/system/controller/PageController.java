package com.uvideo.seckill.system.controller;

import com.uvideo.seckill.system.seckill.good.cross.GoodView;
import com.uvideo.seckill.system.service.seckill.service.SeckillGoodsService;
import com.uvideo.seckill.system.utils.DataUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
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

    private final SeckillGoodsService seckillGoodsService;

    @Autowired
    public PageController(SeckillGoodsService seckillGoodsService) {
        this.seckillGoodsService = seckillGoodsService;
    }

    @RequestMapping(value = "/login_uri.html")
    private String loginUrl() {
        return "login.html";
    }

    @RequestMapping(value = "register_uri.html")
    private String registerUrl() {
        return "register.html";
    }

    @RequestMapping(value = "index.html")
    private ModelAndView indexUrl(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        String loginStatus = (String) request.getSession().getAttribute("username");
        if ("".equals(loginStatus) || loginStatus == null) {
            mv.addObject("status", "unlogin");
        } else {
            mv.addObject("status", "login");
        }
        mv.setViewName("index.html");
        mv.addObject("activity", seckillGoodsService.getGoodsView());
        return mv;
    }

    @RequestMapping(value = "goods_detail_url.html")
    private String goodsDetailUrl(HttpServletRequest request) {
        String loginStatus = (String) request.getSession().getAttribute("username");
        if ("".equals(loginStatus) || loginStatus == null) {
            return "login.html";
        }
        return "goods_detail.html";
    }

    @RequestMapping(value = "goods_detail_url.html/{id}")
    private ModelAndView goodsDetailUrlWithParameter(HttpServletRequest request, @PathVariable(value = "id") Long id) {
        String loginStatus = (String) request.getSession().getAttribute("username");
        ModelAndView mv = new ModelAndView();
        if ("".equals(loginStatus) || loginStatus == null) {
            mv.setViewName("login.html");
            return mv;
        }
        GoodView oneGoodsView = seckillGoodsService.getOneGoodsView(id);
        long timer = DataUtils.timeDiff(oneGoodsView.getSeckillGoods().getStartDate(), oneGoodsView.getSeckillGoods().getEndDate()) / 1000 / 60;
        System.out.println(timer);
        mv.setViewName("goods_detail.html");
        mv.addObject("timer",timer);
        mv.addObject("activity", oneGoodsView);
        return mv;
    }
}



