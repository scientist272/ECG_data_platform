package com.cuhk.ksl.heart.service;

import com.cuhk.ksl.heart.entity.User;
import com.cuhk.ksl.heart.vo.Msg;
import com.cuhk.ksl.heart.vo.UserAddFriendsRequest;

import java.util.List;

public interface UserFriendsService {
    Msg submitAddRequest(UserAddFriendsRequest request);

    Msg getAllAddRequests(String targetUserName);

    Msg acceptAddRequest(String targetUserName, String requestUserName);

    //TODO
    Msg refuseAddRequest(String targetUserName, String requestUserName);

    Msg getAllFriends(String userName);

    Msg getFriendMessages(String user,String friend);
}
