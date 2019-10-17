package com.cuhk.ksl.heart.controller;

import com.alibaba.fastjson.JSON;
import com.cuhk.ksl.heart.dao.UserRepo;
import com.cuhk.ksl.heart.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {
    private final UserRepo userRepo;

    @Autowired
    public TestController(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @GetMapping("/hello")
    public String test(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication!=null)
            System.out.println(authentication.getAuthorities()+authentication.getName());
        return authentication.getAuthorities()+authentication.getName();
    }

    @GetMapping("/entity")
    public String test1() {
        User user = userRepo.findByUserName("user");
        return JSON.toJSONString(user.getRecords());
    }
}
