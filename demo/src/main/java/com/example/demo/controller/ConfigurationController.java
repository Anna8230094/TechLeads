package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/hireandgo/config")
public class ConfigurationController {
    
    @GetMapping("/")
    public String configControl(Model model) {
        model.addAttribute("message", "Please enter your name");
        return "config";
    }

}
