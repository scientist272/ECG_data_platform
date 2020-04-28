package com.cuhk.ksl.heart.controller;

import com.cuhk.ksl.heart.service.UserFriendsService;
import com.cuhk.ksl.heart.vo.Msg;
import com.cuhk.ksl.heart.vo.UserAddFriendsRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/friends",produces = "application/json;charset=UTF-8")
public class UserFriendsController {
    private final UserFriendsService userFriendsService;

    @Autowired
    public UserFriendsController(UserFriendsService userFriendsService) {
        this.userFriendsService = userFriendsService;
    }

    @PostMapping("/add")
    public Msg submitAddRequest(@RequestBody UserAddFriendsRequest request) {
        return userFriendsService.submitAddRequest(request);
    }

    @GetMapping("/get_requests/{targetUserName}")
    public Msg getAllAddRequests(@PathVariable("targetUserName") String targetUserName) {
        return userFriendsService.getAllAddRequests(targetUserName);
    }

    @GetMapping("/accept/{targetUserName}/{requestUserName}")
    public Msg acceptAddRequest(@PathVariable("targetUserName") String targetUserName
            , @PathVariable("requestUserName") String requestUserName) {
        return userFriendsService.acceptAddRequest(targetUserName,requestUserName);
    }

    @GetMapping("/friends/{userName}")
    public Msg getAllFriends(@PathVariable("userName") String userName){
        return userFriendsService.getAllFriends(userName);
    }

    @GetMapping("/messages/{user}/{friend}")
    public Msg getFriendMessages(@PathVariable("user") String user,
                                 @PathVariable("friend")String friend){
        return userFriendsService.getFriendMessages(user,friend);
    }

}
