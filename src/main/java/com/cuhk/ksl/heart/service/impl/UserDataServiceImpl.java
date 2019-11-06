package com.cuhk.ksl.heart.service.impl;

import com.alibaba.fastjson.JSON;
import com.cuhk.ksl.heart.constant.CommonConstant;
import com.cuhk.ksl.heart.dao.UserDataRepo;
import com.cuhk.ksl.heart.dao.UserRepo;
import com.cuhk.ksl.heart.entity.User;
import com.cuhk.ksl.heart.entity.UserData;
import com.cuhk.ksl.heart.service.UserDataService;
import com.cuhk.ksl.heart.util.DateUtil;
import com.cuhk.ksl.heart.vo.KafkaProducerMsg;
import com.cuhk.ksl.heart.vo.Msg;
import com.cuhk.ksl.heart.vo.pm.PMAirRecords;
import com.cuhk.ksl.heart.vo.UserDataRecords;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class UserDataServiceImpl implements UserDataService {
    private final UserDataRepo userDataRepo;
    private final UserRepo userRepo;

    @Override
    public List<UserDataRecords> getUserData(String userName, String startTime, String device) {
        List<UserDataRecords> data = userDataRepo.findByUserNameAndAndStartTimeAndAndDevice(userName, startTime, device);
        return data;
    }

    @Override
    @Transactional
    public int deleteUserDataDaysBefore(int day) {
        return userDataRepo.deleteDatedUserData(DateUtil.getDaysBefore(day));
    }

    @Override
    public List<Integer> generateHeartData(int id) {
        UserDataRecords record = userDataRepo.findUserDataRecordById(id);
        String[] unfilteredData = record.getData().split("\n");
        String[] heartData = unfilteredData[1].split(" ");
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i <heartData.length ; i++) {
            int data = Integer.parseInt(heartData[i]);
            if(data<60000)
                result.add(data);
        }
        return result;
    }

    @Override
    public PMAirRecords generatePMData(int id) {
        UserDataRecords record = userDataRepo.findUserDataRecordById(id);
        PMAirRecords airRecords = PMAirRecords.generatePMAirRecords(record.getData());
        String[] temp = airRecords.getRaw().substring(1,airRecords.getRaw().length()-1).split(",");
        List<Integer> rawData = new ArrayList<>();
        Arrays.stream(temp).forEach(e->rawData.add(Integer.parseInt(e)));
        airRecords.setRawData(rawData);
        return airRecords;
    }

    @Override
    public List<UserDataRecords> getUserDataByDeviceAndUserName(String userName, String device) {
        return userDataRepo.findByUserNameAndDevice(userName,device);
    }

    @Autowired
    public UserDataServiceImpl(UserDataRepo userDataRepo, UserRepo userRepo) {
        this.userDataRepo = userDataRepo;
        this.userRepo = userRepo;
    }

    @Override
    @Transactional
    public String consumeUserData(KafkaProducerMsg userData) {
        User user = userRepo.findByUserName(userData.getUser());
        UserData userData1 = new UserData();
        userData1.setUser(user);
        userData1.setData(userData.getData());
        userData1.setDevice(userData.getDevice());
        userData1.setUserName(userData.getUser());
        userData1.setStartTime(userData.getTimestamp());
        userDataRepo.save(userData1);
        Msg msg = new Msg(CommonConstant.SUCCESS_CODE, CommonConstant.CONSUME_DATA_SUCCESS);
        return JSON.toJSONString(msg);
    }

    @Override
    public int getUserDataCount(String userName) {
        return userDataRepo.countUserDataByUserName(userName);
    }


}
