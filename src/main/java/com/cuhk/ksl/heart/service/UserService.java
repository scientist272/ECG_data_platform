package com.cuhk.ksl.heart.service;

import com.cuhk.ksl.heart.vo.RegisterRequest;
import com.cuhk.ksl.heart.vo.UserRecords;

import java.util.List;

public interface UserService {
    String register(RegisterRequest request);
    List<UserRecords> allUserList();
}
