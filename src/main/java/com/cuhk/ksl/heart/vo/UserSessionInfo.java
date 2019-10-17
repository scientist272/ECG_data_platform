package com.cuhk.ksl.heart.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserSessionInfo {
    private String userName;
    private String role;
    private boolean isLogin;
    private String ipAddress;
}
