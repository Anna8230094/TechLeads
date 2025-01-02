package com.example.demo.mail;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {

    private final EmailService emailService;

    @Autowired
    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @GetMapping("/send-email")
    public CompletableFuture<String> sendEmail(@RequestParam String to,
            @RequestParam String subject,
            @RequestParam String body) {
        return emailService.sendEmail(to, subject, body);
    }

}
