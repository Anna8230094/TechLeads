package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/hireandgo/resultspage")
public class RankingController {

    @GetMapping("/")
    public String rankingControl(Model model) {
        return "resultspage";
    }
    
}
