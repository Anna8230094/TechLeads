package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Maria Spachou
 */
@Controller
@RequestMapping("/hireandgo/home")
public class HomeController {

    // display of home page
    @GetMapping("/")
    public String homeControl() {
        return "home";
    }

}
