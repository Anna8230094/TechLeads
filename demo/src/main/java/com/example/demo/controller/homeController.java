package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/hireandgo/home")
public class homeController {

    @GetMapping("/")
    public String indexControl() {
        return "home";
    }

}
