package com.cuhk.ksl.heart.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

//心电波应用的网页端控制器
@Controller
@RequestMapping("/heart")
public class HeartController {
    @GetMapping("/login")
    public String login() {
        return "heart/login";
    }

    @GetMapping("/list")
    public String userList() {
        return "heart/userList";
    }

    @GetMapping("/dataList/{userName}")
    public String dataList(@PathVariable("userName") String userName, Model model) {
        model.addAttribute("userName", userName);
        return "heart/userDataList";
    }

    @GetMapping("/dataDetails/{userName}/{id}")
    public String dataDetails(@PathVariable("id") int id,
                              @PathVariable("userName") String userName,
                              Model model) {
        model.addAttribute("id", id);
        model.addAttribute("userName", userName);
        return "heart/dataDiagram";
    }
}
