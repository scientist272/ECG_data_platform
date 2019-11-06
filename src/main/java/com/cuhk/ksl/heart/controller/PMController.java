package com.cuhk.ksl.heart.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

//pm应用的网页端控制器
@Controller
@RequestMapping("/pm")
public class PMController {
    @GetMapping("/login")
    public String login(){
        return "pm/login";
    }

    @GetMapping("/list")
    public String userList(){
        return "pm/userList";
    }

    @GetMapping("/dataList/{userName}")
    public String dataList(@PathVariable("userName") String userName, Model model){
        model.addAttribute("userName",userName);
        return "pm/userDataList";
    }
    @GetMapping("/dataDetails/{id}")
    public String dataDetails(@PathVariable("id") int id,Model model){
        model.addAttribute("id",id);
        return "pm/dataDiagram";
    }
}
