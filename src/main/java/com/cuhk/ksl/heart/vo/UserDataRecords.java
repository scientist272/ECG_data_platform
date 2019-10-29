package com.cuhk.ksl.heart.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
//用于返回用户数据的值对象
public class UserDataRecords {
    private String data;
    private String startTime;
    private String device;
}
