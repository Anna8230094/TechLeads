package com.example.demo.controller;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.files.FileStorageService;
import com.example.demo.files.Files;
import com.example.demo.files.FilesStorageRepository;
import com.example.demo.model.User;

import ch.qos.logback.core.model.Model;

@Controller
public class RegistrationFormController {

    @Autowired
    FilesStorageRepository filesStorageService;

    @GetMapping("/hireandgo/home/registrationform")
    public String registrationControl() {
        return "registrationform";
    }

    @PostMapping("/registrationform") 
    public String handleRegistration(@RequestParam("file") List <MultipartFile> file, @ModelAttribute User user ,Model model) {
        System.out.println("Name:" + user.getName());
        System.out.println("Field:" + user.getField());
        System.out.println("Email:" + user.getEmail());
        System.out.println("Hard Skills:" + user.getHardSkills());
        System.out.println("Soft Skills:" + user.getSoftSkills());
        System.out.println("Other Traits:" + user.getOtherTraits());

        try{
            filesStorageService.save(file);
            System.out.println(",,");
           file.forEach(f-> System.out.println(f.getOriginalFilename()));
        } catch (Exception e) {
            System.err.println("the upload of file is not possible");
        }
        //to look how to print all the getting files
        return "success";
    }
}
