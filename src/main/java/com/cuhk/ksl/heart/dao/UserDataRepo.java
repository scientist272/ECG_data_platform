package com.cuhk.ksl.heart.dao;


import com.cuhk.ksl.heart.entity.UserData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserDataRepo extends JpaRepository<UserData,Integer> {
     List<UserData> findByUserName(String userName);
     Integer countUserDataByUserName(String userName);
     List<UserData> findByUserNameAndAndStartTimeAndAndDevice(String userName,String startTime,String device);
}
