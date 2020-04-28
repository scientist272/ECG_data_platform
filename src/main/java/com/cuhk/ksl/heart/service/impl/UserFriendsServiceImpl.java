package com.cuhk.ksl.heart.service.impl;

import com.cuhk.ksl.heart.cache.UserFriendsCache;
import com.cuhk.ksl.heart.constant.CommonConstant;
import com.cuhk.ksl.heart.dao.UserRepo;
import com.cuhk.ksl.heart.entity.User;
import com.cuhk.ksl.heart.service.MessageService;
import com.cuhk.ksl.heart.service.UserFriendsService;
import com.cuhk.ksl.heart.util.DateUtil;
import com.cuhk.ksl.heart.vo.Msg;
import com.cuhk.ksl.heart.vo.UserAddFriendsRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

@Service
public class UserFriendsServiceImpl implements UserFriendsService {


    private final UserRepo userRepo;

    private final MessageService messageService;

    public Map<String, BlockingQueue<UserAddFriendsRequest>> userFriendsCache = UserFriendsCache.userAddFriendsRequestCache;

    @Autowired
    public UserFriendsServiceImpl(UserRepo userRepo, MessageService messageService) {
        this.userRepo = userRepo;
        this.messageService = messageService;
    }

    @Override
    public Msg submitAddRequest(UserAddFriendsRequest request) {
        request.setRequestTime(DateUtil.generalSimpleDateFormat());
        String targetUserName = request.getTargetUserName();
        if (userFriendsCache.containsKey(targetUserName)) {
            BlockingQueue<UserAddFriendsRequest> blockingQueue = userFriendsCache.get(targetUserName);
            try {
                blockingQueue.put(request);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            BlockingQueue<UserAddFriendsRequest> blockingQueue = new LinkedBlockingDeque<>();
            try {
                blockingQueue.put(request);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            userFriendsCache.put(targetUserName, blockingQueue);
        }
        return new Msg(CommonConstant.SUCCESS_CODE, CommonConstant.SUCCESS_SUBMIT_ADD_FRIENDS_REQUEST);
    }

    @Override
    public Msg getAllAddRequests(String targetUserName) {
        BlockingQueue<UserAddFriendsRequest> blockingQueue = userFriendsCache.get(targetUserName);
        List<UserAddFriendsRequest> result = new ArrayList<>();
        if (!blockingQueue.isEmpty()) {
            while (!blockingQueue.isEmpty()) {
                try {
                    result.add(blockingQueue.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return new Msg(CommonConstant.SUCCESS_CODE, result);
        } else {
            return new Msg(CommonConstant.ERROR_CODE, CommonConstant.EMPTY_ADD_REQUEST);
        }
    }

    @Override
    @Transactional
    public Msg acceptAddRequest(String targetUserName, String requestUserName) {
        User targetUser = userRepo.findByUserName(targetUserName);
        User requestUser = userRepo.findByUserName(requestUserName);

        if (targetUser != null && requestUser != null) {
            targetUser.getFriends().add(requestUser);
            requestUser.getFriends().add(targetUser);
            userRepo.save(targetUser);
            userRepo.save(requestUser);
            return new Msg(CommonConstant.SUCCESS_CODE,CommonConstant.SUCCESS_ACCEPT_ADD_FRIENDS_REQUEST);
        }
        return new Msg(CommonConstant.ERROR_CODE,CommonConstant.ADD_FRIEND_FAIL);
    }

    @Override
    public Msg refuseAddRequest(String targetUserName, String requestUserName) {
        return null;
    }

    @Override
    @Transactional
    public Msg getAllFriends(String userName) {
        User user = userRepo.findByUserName(userName);
        if(user!=null){
            return new Msg(CommonConstant.SUCCESS_CODE,user.getFriends());
        }
        return new Msg(CommonConstant.SUCCESS_CODE,CommonConstant.USER_NOT_EXISTS);
    }

    @Override
    public Msg getFriendMessages(String user, String friend) {
        return messageService.getMessageBySenderAndReceiver(friend,user);
    }
}
