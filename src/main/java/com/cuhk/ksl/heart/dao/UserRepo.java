package com.cuhk.ksl.heart.dao;

import com.cuhk.ksl.heart.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User,Integer> {
     User findByUserName(String userName);
     Optional<User> findById(Integer id);
}
