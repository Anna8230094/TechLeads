package com.example.demo.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailAuthenticationException;
import org.springframework.mail.MailParseException;
import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {

    private final JavaMailSender emailSender;

    @Value("${website.url}")
    private String websiteLink;

    @Autowired
    public EmailService(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    public void sendEmail(String to, String subject, String text) throws MessagingException {

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
            

            String htmlContent = "<p>Hello ,</p>" +
                    "<p>Thank you for using HireandGo. Please click the link below to view your confirmation:</p>" +
                    "<p><a href=\"" + websiteLink + "\">View Confirmation</a></p>" +
                    "<p>Best regards,<br>HireandGo Team</p>";

            helper.setText(htmlContent, true);

            emailSender.send(message);

        } catch (IllegalArgumentException e) {
            System.err.println("Input validation failed: " + e.getMessage());
            throw e; // Rethrow to indicate client-side input failure
        } catch (MailAuthenticationException e) {
            // Handle authentication failures
            System.err.println("Authentication failed: " + e.getMessage());
            throw new RuntimeException("Email authentication failed. Please check credentials.", e);
        } catch (MailSendException e) {
            // Handle send failures
            System.err.println("Failed to send email: " + e.getMessage());
            throw new RuntimeException("Unable to send email. SMTP server issue.", e);
        } catch (MailParseException e) {
            // Handle email content issues
            System.err.println("Invalid email content: " + e.getMessage());
            throw new RuntimeException("Email content error.", e);
        } catch (MessagingException e) {
            // Handle general messaging exceptions
            System.err.println("Unexpected messaging error: " + e.getMessage());
            throw new RuntimeException("Unexpected error while sending email.", e);
        }
        

    }
}
