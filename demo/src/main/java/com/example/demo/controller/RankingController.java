package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Maria Spachou
 */
@Controller
@RequestMapping("/hireandgo/home")
public class RankingController {

    // display of ranking page
    @GetMapping("/ranking")
    public String rankingControl(Model model) {
        model.addAttribute("message", "Please enter your email");
        return "ranking";
    }
    
}
