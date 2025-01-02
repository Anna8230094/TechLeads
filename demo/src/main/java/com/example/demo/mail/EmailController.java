package com.example.demo.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.mail.MessagingException;

/**
 * This class represents my class in Java.
 * @author Aggeliki Despoina Megalou
 * @version 1.0
 */
@RestController
public class EmailController {
     
    private final EmailService emailService;

   
    @Autowired
    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @GetMapping("/send-email")
    public String sendEmail(
            @RequestParam String to,
            @RequestParam String subject,
            @RequestParam String body) throws MessagingException {

        emailService.sendEmail(to, subject, body);
        return "Email sent successfully!";
    }
}
