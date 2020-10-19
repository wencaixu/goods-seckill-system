package com.uvideo.seckill.system;

public enum  LoginStatus {
    unlogin("未登录"),
    login("已登录");

    private String status;

    LoginStatus(String status) {
        this.status = status;
    }
}
