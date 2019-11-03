package com.cuhk.ksl.heart.controller;

import com.cuhk.ksl.heart.service.UserDataService;
import com.cuhk.ksl.heart.vo.UserDataRecords;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/data")
public class DataController {
    private final UserDataService userDataService;

    @Autowired
    public DataController(UserDataService userDataService) {
        this.userDataService = userDataService;
    }

    @GetMapping("/count/{userName}")
    public String getUserDataCount(@PathVariable("userName") String userName){
        return ("{"+"\""+"count"+"\""+":"+String.valueOf(userDataService.getUserDataCount(userName))+"}");
    }

    @GetMapping("/{userName}/{startTime}/{device}")
    public List<UserDataRecords> getUserDataRecords(@PathVariable("userName") String userName,
                                                    @PathVariable("startTime") String startTime,
                                                    @PathVariable("device") String device){
        return userDataService.getUserData(userName,startTime,device);
    }

    @GetMapping("/{userName}/{device}")
    public List<UserDataRecords> getUserDataRecordsByDeviceAndUserName(@PathVariable("userName") String userName,
                                                                       @PathVariable("device") String device){
        return userDataService.getUserDataByDeviceAndUserName(userName,device);
    }
}
