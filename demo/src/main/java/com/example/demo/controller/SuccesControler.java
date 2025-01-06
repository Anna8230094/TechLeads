package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/hireandgo/home/registrationform/")
public class SuccesControler {

    @GetMapping("/success")
    public String handleSucces(Model model, @RequestParam String username, @PathVariable Long id) {
        return "success";
    }

}
