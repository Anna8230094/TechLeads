package com.example.demo.controller;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.database.reasearcher.ResearcherService;
import com.example.demo.database.user.Users;
import com.example.demo.database.user.UsersService;
import com.example.demo.openai.service.OpenAiService;

import ch.qos.logback.core.model.Model;

@Controller
public class RegistrationFormController {

    @Autowired
    UsersService usersService;

    @Autowired
    ResearcherService researcherService;

    @Autowired
    OpenAiService openAiService;

    @GetMapping("/hireandgo/home/registrationform")
    public String registrationControl() {
        return "registrationform";
    }

    @PostMapping("/registrationform")
    public String handleRegistration(@RequestParam("file") List<MultipartFile> files, @ModelAttribute Users user, Model model) {
        try {
            CompletableFuture<Void> save = usersService.saveUsers(user);
            CompletableFuture.allOf(save).join();
            System.out.println("Name:" + user.getName());
            System.out.println("Field:" + user.getField());
            System.out.println("Email:" + user.getEmail());
            System.out.println("Hard Skills:" + user.getHardSkills());
            System.out.println("Soft Skills:" + user.getSoftSkills());
            System.out.println("Other Traits:" + user.getOtherTraits());
            new Thread(new Runnable() {

                public void run() {
                    try {
                        openAiService.startRankingProcessing(files, user);
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }).start();
        } catch (Exception e) {
            System.err.println(e.toString());
            System.err.println("Unable to save user");
        }

        return "success";
    }
}
