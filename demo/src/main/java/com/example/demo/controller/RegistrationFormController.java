package com.example.demo.controller;

import java.util.List;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.database.user.Users;
import com.example.demo.database.user.UsersService;
import com.example.demo.openai.service.OpenAiService;

import ch.qos.logback.core.model.Model;

/**
 * @author Maria Spachou
 */
@Controller
public class RegistrationFormController {

    @Autowired
    UsersService usersService;

    @Autowired
    OpenAiService openAiService;

    public CompletableFuture<Void> promise = new CompletableFuture<>();

    // display of registrationform page
    @GetMapping("/hireandgo/home/registrationform")
    public String registrationControl() {
        return "registrationform";
    }

    // storing user's data and displaying them
    @PostMapping("/registrationform")
    public String handleRegistration(@RequestParam("file") List<MultipartFile> files, @ModelAttribute Users user,
            Model model) throws Exception {

        try {
            System.out.println("Name:" + user.getName());
            System.out.println("Field:" + user.getField());
            System.out.println("Email:" + user.getEmail());
            System.out.println("Hard Skills:" + user.getHardSkills());
            System.out.println("Soft Skills:" + user.getSoftSkills());
            System.out.println("Other Traits:" + user.getOtherTraits());

            for (MultipartFile file : files) {
                System.out.println("File Name: " + file.getOriginalFilename());
                System.out.println("File Size: " + file.getBytes());
            }

            promise = usersService.saveUsers(user);
            usersService.saveUsers(user).join();

           promise = asyncStartRankingProcess(files, user);

            /*
             * CompletableFuture.runAsync(() -> {
             * try {
             * System.out.print("MMMMMMMMMMMMMMMMMM"+files.get(0).getBytes());
             * openAiService.startRankingProcess(files, user);
             * } catch (Exception e) {
             * System.err.println("Error during ranking process: " + e.getMessage());
             * }
             * });
             */
            

        } catch (CancellationException e) {
            System.err.println(e.toString());
            System.err.println("Unable to save user");
        }

        return "success";
    }


    @Async
    public CompletableFuture<Void> asyncStartRankingProcess(List<MultipartFile> files, Users user) {
        return CompletableFuture.runAsync(() -> {
            try {
                openAiService.startRankingProcess(files, user);
            } catch (Exception e) {
                System.err.println("Error during ranking process: " + e.getMessage());
            }
        });
    }
}
