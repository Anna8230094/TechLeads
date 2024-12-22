package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.User;

@Controller
public class RegistrationFormController {

    @GetMapping("/hireandgo/home/registrationform")
    public String registrationControl() {
        return "registrationform";
    }

    @PostMapping("/registrationform")
    public String handleRegistration(@ModelAttribute User user, Model model) {
        System.out.println("Name:" + user.getName());
        System.out.println("Field:" + user.getField());
        System.out.println("Email:" + user.getEmail());
        System.out.println("Hard Skills:" + user.getHardSkills());
        System.out.println("Soft Skills:" + user.getSoftSkills());
        System.out.println("Other Traits:" + user.getOtherTraits());
        //System.out.println("Files:" + user.getFiles());
        model.addAttribute("message", "User registered succesfully!");
        return "success";
    }
}
