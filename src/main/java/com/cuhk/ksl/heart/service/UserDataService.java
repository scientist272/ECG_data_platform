package com.cuhk.ksl.heart.service;

import com.cuhk.ksl.heart.vo.KafkaProducerMsg;
import com.cuhk.ksl.heart.vo.pm.PMAirRecords;
import com.cuhk.ksl.heart.vo.UserDataRecords;

import java.util.List;

public interface UserDataService {
    //卡夫卡消费者，消费消息到数据库存储
    String consumeUserData(KafkaProducerMsg userData);
    //查看用户的数据数量
    int getUserDataCount(String userName);
    //获取用户数据
    List<UserDataRecords> getUserData(String userName, String startTime, String device);

    //删除几天前的用户数据
    int deleteUserDataDaysBefore(int day);

    //生成心电波数据的数组,用于前端绘图
    List<Integer> generateHeartData(int id);

    //生成PM数据的数组，用于前端绘图
    PMAirRecords generatePMData(int id);

    //根据用户名和应用名查找数据
    List<UserDataRecords> getUserDataByDeviceAndUserName(String userName,String device);
}
