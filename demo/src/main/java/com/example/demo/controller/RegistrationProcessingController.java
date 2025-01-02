package com.example.demo.controller;

import com.example.demo.database.user.Users;
import com.example.demo.model.User;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class RegistrationProcessingController {

    @PostMapping("/registrationform")
    public String handleRegistration(@ModelAttribute Users user,@RequestParam List<MultipartFile> files, Model model) {
        System.out.println("Name:" + user.getName());
        System.out.println("Field:" + user.getField());
        System.out.println("Email:" + user.getEmail());
        System.out.println("Hard Skills:" + user.getHardSkills());
        System.out.println("Soft Skills:" + user.getSoftSkills());
        System.out.println("Other Traits:" + user.getOtherTraits());
        if (files != null && !files.isEmpty()) {
            for (MultipartFile file : files) {
                System.out.println("File Name: " + file.getOriginalFilename());
            }
        }
        model.addAttribute("message", "User registered succesfully!");
        return "success";
    }
}
