package com.cuhk.ksl.heart.controller;

import com.alibaba.fastjson.JSON;
import com.cuhk.ksl.heart.service.UserService;
import com.cuhk.ksl.heart.vo.RegisterRequest;
import com.cuhk.ksl.heart.vo.UserSessionInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    //用于查看用户登录状态
    @GetMapping("/session")
    public String getSessionInfo(HttpServletRequest request){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean isLogin = false;
        if(!authentication.getName().equals("anonymousUser")){
            isLogin = true;
        }
        return JSON.toJSONString(
                new UserSessionInfo(
                        authentication.getName(),
                        JSON.toJSONString(authentication.getAuthorities()),
                        isLogin,request.getRemoteAddr()));
    }

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest registerRequest){
        return userService.register(registerRequest);
    }
}
