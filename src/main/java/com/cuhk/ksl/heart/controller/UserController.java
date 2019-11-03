package com.cuhk.ksl.heart.controller;

import com.alibaba.fastjson.JSON;
import com.cuhk.ksl.heart.entity.User;
import com.cuhk.ksl.heart.service.UserService;
import com.cuhk.ksl.heart.vo.RegisterRequest;
import com.cuhk.ksl.heart.vo.UserRecords;
import com.cuhk.ksl.heart.vo.UserSessionInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
    public UserSessionInfo getSessionInfo(HttpServletRequest request){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean isLogin = false;
        if(!authentication.getName().equals("anonymousUser")){
            isLogin = true;
        }
        return
                new UserSessionInfo(
                        authentication.getName(),
                        JSON.toJSONString(authentication.getAuthorities()),
                        isLogin,request.getRemoteAddr());
    }

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest registerRequest){
        return userService.register(registerRequest);
    }

    @GetMapping("/list")
    public List<UserRecords> userList(){
        return userService.allUserList();
    }
}
