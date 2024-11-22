package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {

    private JavaMailSender emailSender;

    @Value("${website.url}")
    private String websiteLink;
   
   
    @Autowired
    public EmailService(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    public void sendEmail(String to, String subject, String text)throws MessagingException {

        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(to);
        helper.setSubject(subject);
       

        String htmlContent = "<p>Hello ,</p>" +
                "<p>Thank you for using HireandGo. Please click the link below to view your confirmation:</p>" +
                "<p><a href=\"" + websiteLink + "\">View Confirmation</a></p>" +
                "<p>Best regards,<br>HireandGo Team</p>";

        helper.setText(htmlContent, true);
        emailSender.send(message);


    }

}
