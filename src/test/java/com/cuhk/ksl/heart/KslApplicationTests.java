package com.cuhk.ksl.heart;

import com.alibaba.fastjson.JSON;
import com.cuhk.ksl.heart.dao.MessageDao;
import com.cuhk.ksl.heart.dao.RoleRepo;
import com.cuhk.ksl.heart.dao.UserDataRepo;
import com.cuhk.ksl.heart.dao.UserRepo;
import com.cuhk.ksl.heart.entity.Message;
import com.cuhk.ksl.heart.entity.Role;
import com.cuhk.ksl.heart.entity.User;
import com.cuhk.ksl.heart.entity.UserData;
import com.cuhk.ksl.heart.service.MessageService;
import com.cuhk.ksl.heart.service.UserDataService;
import com.cuhk.ksl.heart.service.UserService;
import com.cuhk.ksl.heart.util.DateUtil;
import com.cuhk.ksl.heart.vo.Msg;
import com.cuhk.ksl.heart.vo.RegisterRequest;
import com.cuhk.ksl.heart.vo.UserDataRecords;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

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
    @Autowired
    private UserDataService userDataService;
    @Autowired
    private MessageDao messageDao;
    @Autowired
    private MessageService messageService;
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

    @Test
    public void testDataRepo(){
        List<UserDataRecords> records = userDataRepo.findByUserNameAndAndStartTimeAndAndDevice("user","2019-10-10 19:56:46","heart");
        System.out.println(JSON.toJSONString(records));
    }

    @Test
    public void testDataUtil(){
        String date = DateUtil.getDaysBefore(7);
        System.out.println(date);
    }
    @Test
    public void testDeleteData(){
        int number = userDataRepo.deleteDatedUserData(DateUtil.getDaysBefore(3));
        System.out.println(number);
    }

    @Test
    public void testGenWeatherData(){
        List<Integer> heartData = userDataService.generateHeartData(39);
        heartData.stream().forEach(System.out::println);
    }

    @Test
    @Transactional
    public void testSaveMessage(){
        Message message = new Message();
        message.setContent("test");
        message.setReceiver("test");
        message.setSender("test");
        message.setTitle("test");
        message.setUser(userRepo.findById(1).get());
        message = messageDao.save(message);
        System.out.println(message.getId());
    }

    @Test
    public void testSendMessage(){
        Message message = new Message();
        message.setContent("test");
        message.setReceiver("test");
        message.setSender("test");
        message.setTitle("test");
        message.setSender("user");
        System.out.println(JSON.toJSONString(messageService.sendMessage(message,"ksl-msg")));
    }
}

