//package com.cuhk.ksl.heart.service;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//@Component
//@Slf4j
//public class ScheduledTasks {
//
//    private final UserDataService userDataService;
//
//    @Autowired
//    public ScheduledTasks(UserDataService userDataService) {
//        this.userDataService = userDataService;
//    }
//
//    //清除几天前的过期数据
//    @Scheduled(initialDelay = 14*86400000,fixedRate = 14*86400000)
//    public void deleteDatedUserData() {
//        log.info("delete user data before 1 day, totally deleted count:{} ", userDataService.deleteUserDataDaysBefore(14));
//    }
//}
