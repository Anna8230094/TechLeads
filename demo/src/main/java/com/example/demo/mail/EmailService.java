package com.example.demo.mail;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import jakarta.mail.internet.MimeMessage;

/**
 * This class represents my class in Java.
 * 
 * @author Aggeliki Despoina Megalou
 * @version 1.0
 */
@Service
public class EmailService {

    private final JavaMailSender emailSender;

    private String websiteLink = " http://localhost:8081/hireandgo/ranking/";

    @Autowired
    public EmailService(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    /*
     * public void sendEmail(String to, String subject, String text) throws
     * MessagingException {
     */
    @Async
    public CompletableFuture<String> sendEmail(String to, String subject, String text, String user) {

        try {
            // Input validation
            if (to == null || to.isEmpty() || !to.contains("@")) {
                throw new IllegalArgumentException("Invalid email address: " + to);
            }
            if (subject == null || subject.isEmpty()) {
                throw new IllegalArgumentException("Subject cannot be null or empty");
            }
            if (text == null || text.isEmpty()) {
                throw new IllegalArgumentException("Text cannot be null or empty");
            }

            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(to);
            helper.setSubject(subject);

            String htmlContent = "<p>Hello " + user + ",</p>" +
                    "<p>Thank you for using HireandGo. Please click the link below to view your confirmation:</p>" +
                    "<p><a href=\"" + websiteLink + "\">View Confirmation</a></p>" +
                    "<p>Best regards,<br>HireandGo Team</p>";

            helper.setText(htmlContent, true);

            emailSender.send(message);
            return CompletableFuture.completedFuture("Email sent successfully!");

        } catch (Exception e) {
            return CompletableFuture.failedFuture(e);
        }

    }
}
