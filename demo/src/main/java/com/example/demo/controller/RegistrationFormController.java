package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.database.reasearcher.ResearcherService;
import com.example.demo.database.user.UsersService;

@Controller
@RequestMapping("/hireandgo/home/")
public class RegistrationFormController {
    @Autowired
   UsersService usersService;


   @Autowired
    ResearcherService researcerService;
    
    @GetMapping("/registrationform.html")
    public String registrationControl() {
        return "registrationform";
    }
    

}
