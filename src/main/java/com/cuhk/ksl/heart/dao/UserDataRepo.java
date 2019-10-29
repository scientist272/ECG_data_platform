package com.cuhk.ksl.heart.dao;


import com.cuhk.ksl.heart.entity.UserData;
import com.cuhk.ksl.heart.vo.UserDataRecords;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserDataRepo extends JpaRepository<UserData,Integer> {
     List<UserData> findByUserName(String userName);
     Integer countUserDataByUserName(String userName);

     @Query(value = "select new com.cuhk.ksl.heart.vo.UserDataRecords(u.data,u.startTime,u.device) " +
             "from UserData u where userName = :userName and startTime = :startTime and device = :device")
     List<UserDataRecords> findByUserNameAndAndStartTimeAndAndDevice(@Param("userName")
                                                                      String userName,
                                                                     @Param("startTime") String startTime,
                                                                     @Param("device") String device);
}
