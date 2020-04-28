package com.cuhk.ksl.heart.vo;

import lombok.Data;

@Data
public class UserAddFriendsRequest {
    private String requestUserName;
    private String targetUserName;
    private String requestTime;
}
