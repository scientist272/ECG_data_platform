package com.cuhk.ksl.heart.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
//User对应的值对象，用于返回到页面显示
public class UserRecords {
    private Integer id;
    private String userName;
}
