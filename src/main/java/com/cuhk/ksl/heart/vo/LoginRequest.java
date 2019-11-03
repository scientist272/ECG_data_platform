package com.cuhk.ksl.heart.vo;

import lombok.Data;

@Data
//登录请求的值对象
public class LoginRequest {
    private String userName;
    private String password;
}
