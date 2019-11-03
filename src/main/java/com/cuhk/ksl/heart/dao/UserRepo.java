package com.cuhk.ksl.heart.dao;

import com.cuhk.ksl.heart.entity.User;
import com.cuhk.ksl.heart.vo.UserRecords;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepo extends JpaRepository<User,Integer> {

     @Query(value = "select new com.cuhk.ksl.heart.vo.UserRecords(u.id,u.userName) from User u")
     List<UserRecords> findAllUserRecords();
     User findByUserName(String userName);
     Optional<User> findById(Integer id);
}
