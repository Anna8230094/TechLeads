package com.example.demo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequestMapping("/hireandgo/config")
public class ConfigurationController {
    
    @GetMapping("/")
    public String ConfigControl(Model model) {
        model.addAttribute("message", "Please enter your name");
        return "config";
    }

}
