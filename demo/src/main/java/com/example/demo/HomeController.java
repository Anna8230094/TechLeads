package com.example.demo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequestMapping("/hireandgo/home")
public class HomeController {

    @GetMapping("/")
    public String homeControl(Model model) {
        model.addAttribute("message", "Welcome to Hire&Go!");
        return "home";
    }

}
