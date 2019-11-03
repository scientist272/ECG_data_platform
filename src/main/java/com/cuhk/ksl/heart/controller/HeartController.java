package com.cuhk.ksl.heart.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/heart")
public class HeartController {
    @GetMapping("/login")
    public String login(){
        return "heart/login";
    }

    @GetMapping("/list")
    public String userList(){
        return "heart/userList";
    }

    @GetMapping("/dataList/{userName}")
    public String dataList(@PathVariable("userName") String userName, Model model){
        model.addAttribute("userName",userName);
        return "heart/userDataList";
    }
}
