package com.cuhk.ksl.heart.dao;


import com.cuhk.ksl.heart.entity.UserData;
import com.cuhk.ksl.heart.vo.UserDataRecords;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserDataRepo extends JpaRepository<UserData, Integer> {
    List<UserData> findByUserName(String userName);

    Integer countUserDataByUserName(String userName);

    @Query(value = "select new com.cuhk.ksl.heart.vo.UserDataRecords(u.data,u.startTime,u.device) " +
            "from UserData u where userName = :userName and startTime = :startTime and device = :device")
    List<UserDataRecords> findByUserNameAndAndStartTimeAndAndDevice(@Param("userName")
                                                                            String userName,
                                                                    @Param("startTime") String startTime,

                                                                    @Param("device") String device);

    //删除过期的数据
    @Modifying
    @Query(value = "delete from UserData u where u.startTime<:timeAgo")
    int deleteDatedUserData(@Param("timeAgo") String timeAgo);

    //通过用户名和应用来查看数据
    @Query(value = "select new com.cuhk.ksl.heart.vo.UserDataRecords(u.startTime,u.device) " +
            "from UserData u where userName = :userName and device = :device")
    List<UserDataRecords> findByUserNameAndDevice(@Param("userName") String userName,@Param("device")String device);

}
