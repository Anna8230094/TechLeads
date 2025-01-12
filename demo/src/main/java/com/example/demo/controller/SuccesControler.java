package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/hireandgo/home/registrationform/")
public class SuccesControler {

    @GetMapping("/success")
    public String handleSucces() {
        return "success";
    }

}
