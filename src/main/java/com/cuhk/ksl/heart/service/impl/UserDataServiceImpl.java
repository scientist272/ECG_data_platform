package com.cuhk.ksl.heart.service.impl;

import com.alibaba.fastjson.JSON;
import com.cuhk.ksl.heart.constant.CommonConstant;
import com.cuhk.ksl.heart.dao.UserDataRepo;
import com.cuhk.ksl.heart.dao.UserRepo;
import com.cuhk.ksl.heart.entity.User;
import com.cuhk.ksl.heart.entity.UserData;
import com.cuhk.ksl.heart.service.UserDataService;
import com.cuhk.ksl.heart.vo.KafkaProducerMsg;
import com.cuhk.ksl.heart.vo.Msg;
import com.cuhk.ksl.heart.vo.UserDataRecords;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDataServiceImpl implements UserDataService {
    private final UserDataRepo userDataRepo;
    private final UserRepo userRepo;

    @Override
    public List<UserDataRecords> getUserData(String userName, String startTime, String device) {
        List<UserData> data = userDataRepo.findByUserNameAndAndStartTimeAndAndDevice(userName, startTime, device);
        List<UserDataRecords> result = new ArrayList<>();
        data.forEach((userData) -> {
            result.add(
                    new UserDataRecords(userData.getData(),
                            userData.getStartTime(),
                            userData.getDevice()));
        });
        return result;
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