package com.cuhk.ksl.heart.service.impl;

import com.alibaba.fastjson.JSON;
import com.cuhk.ksl.heart.dao.RoleRepo;
import com.cuhk.ksl.heart.dao.UserRepo;
import com.cuhk.ksl.heart.entity.User;
import com.cuhk.ksl.heart.service.UserService;
import com.cuhk.ksl.heart.vo.Msg;
import com.cuhk.ksl.heart.vo.RegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;
    private final RoleRepo roleRepo;

    @Autowired
    public UserServiceImpl(UserRepo userRepo, RoleRepo roleRepo) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
    }

    @Override
    @Transactional
    public String register(RegisterRequest request) {
        if (userRepo.findByUserName(request.getUserName()) == null) {
            User user = new User();
            user.setUserName(request.getUserName());
            user.setPassword(new BCryptPasswordEncoder().encode(request.getPassword()));
            user.setRoles(Collections.singletonList(roleRepo.findByName("BASIC")));
            userRepo.save(user);
            return JSON.toJSONString(new Msg("0", "注册成功"));
        }else{
            return JSON.toJSONString(new Msg("-1","用户名已被注册"));
        }
    }
}
