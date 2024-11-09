package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Hire&Go/home")
public class Controller {
  
    @GetMapping("/")
    public String home() {
        return "home";
    }

}
