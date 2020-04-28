package com.cuhk.ksl.heart.cache;

import com.cuhk.ksl.heart.vo.UserAddFriendsRequest;

import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;

public class UserFriendsCache {
    public static final Map<String, BlockingQueue<UserAddFriendsRequest>> userAddFriendsRequestCache = new ConcurrentHashMap<>();
}
