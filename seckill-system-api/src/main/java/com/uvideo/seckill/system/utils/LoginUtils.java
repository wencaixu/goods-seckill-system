package com.uvideo.seckill.system.utils;

import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

public class LoginUtils {

    public static boolean isLogin(HttpServletRequest request){
        String username = (String) request.getAttribute("username");
        return StringUtils.isEmpty(username);
    }

    public static String getSessionUsername(HttpServletRequest request) {

        return (String) request.getSession().getAttribute("username");
    }

    public static boolean isLogin(String loginStatus) {

        return "".equals(loginStatus) || loginStatus == null;
    }

    public static void setUserIndexStatus(HttpServletRequest request, ModelAndView mv) {
        String loginStatus = LoginUtils.getSessionUsername(request);
        if (LoginUtils.isLogin(loginStatus)) {
            mv.addObject("status", "unlogin");
        } else {
            mv.addObject("status", "login");
        }
    }

}
