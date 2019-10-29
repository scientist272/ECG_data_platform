package com.cuhk.ksl.heart;

import com.alibaba.fastjson.JSON;
import com.cuhk.ksl.heart.dao.RoleRepo;
import com.cuhk.ksl.heart.dao.UserDataRepo;
import com.cuhk.ksl.heart.dao.UserRepo;
import com.cuhk.ksl.heart.entity.Role;
import com.cuhk.ksl.heart.entity.User;
import com.cuhk.ksl.heart.entity.UserData;
import com.cuhk.ksl.heart.service.UserService;
import com.cuhk.ksl.heart.vo.Msg;
import com.cuhk.ksl.heart.vo.RegisterRequest;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class KslApplicationTests {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private RoleRepo roleRepo;
    @Autowired
    private UserDataRepo userDataRepo;
    @Autowired
    private UserService userService;

    @Test
    public void contextLoads() {
        String userName = "user";
        User user = userRepo.findByUserName(userName);
        System.out.println(user.toString());
    }

    @Test
    public void testRoleRepo() {
        String name = "BASIC";
        Role role = roleRepo.findByName(name);
        System.out.println(role.toString());
    }

    @Test
    public void testEncode() {
        String password = new BCryptPasswordEncoder().encode("password");
        log.info("password:{}",password);
    }


    @Test
    public void testSaveUser() {
        User user = new User();
        user.setUserName("testName");
        user.setPassword("123456");
        List<Role> roleList = new ArrayList<>();
        Role role1 = new Role();
        role1.setName("TEST_ROLE");
        roleRepo.save(role1);
        roleList.add(role1);
        user.setRoles(roleList);
        userRepo.save(user);
    }

    @Test
    public void testSaveUserData() {
        User user = userRepo.findByUserName("testName");
        UserData data = new UserData();
        data.setUserName(user.getUserName());
        data.setDevice("test1");
        data.setData("test1");
        data.setStartTime("11");
        data.setUser(user);
        userDataRepo.save(data);
    }


    @Test
    public void testRegister(){
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUserName("test1");
        registerRequest.setPassword("password");
        System.out.println(userService.register(registerRequest));
    }

    @Test
    public void testCount(){
        System.out.println(userDataRepo.countUserDataByUserName("user"));
    }


}

