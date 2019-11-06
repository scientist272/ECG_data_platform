package com.cuhk.ksl.heart.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
//用于返回用户数据的值对象
public class UserDataRecords {
    private Integer id;
    private String data;
    private String startTime;
    private String device;
    public UserDataRecords(String startTime,String device){
        this.startTime = startTime;
        this.device = device;
    }
    public UserDataRecords(Integer id,String startTime,String device){
        this.id = id;
        this.startTime = startTime;
        this.device = device;
    }
}
