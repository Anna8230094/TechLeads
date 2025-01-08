package com.example.demo.email;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.concurrent.CompletionException;

import org.mockito.MockitoAnnotations;
import org.springframework.mail.MailAuthenticationException;
import org.springframework.mail.MailParseException;
import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSender;

import com.example.demo.mail.EmailService;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

class EmailServiceTest {

    @Mock
    private JavaMailSender javaMailSender;

    @InjectMocks
    private EmailService emailService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // Test: Valid Inputs
    @Test
    void testSendEmailWithValidInputs() throws MessagingException {
        // Mock MimeMessage
        MimeMessage mimeMessage = mock(MimeMessage.class);
        when(javaMailSender.createMimeMessage()).thenReturn(mimeMessage);

        String to = "test@example.com";
        String subject = "Valid Subject";
        String text = "Valid Body";

        // Act
        emailService.sendEmail(to, subject, text);

        // Verify
        verify(javaMailSender, times(1)).createMimeMessage();
        verify(javaMailSender, times(1)).send(mimeMessage);
    }

    // Test: Invalid Email Address
    @Test
    void testSendEmailWithInvalidEmail() {
        String invalidEmail = "invalid-email";
        String subject = "Test Subject";
        String text = "Test Body";

        Exception exception = assertThrows(CompletionException.class, () -> {
            emailService.sendEmail(invalidEmail, subject, text).join();
        });
        assertEquals("java.lang.IllegalArgumentException: Invalid email address: invalid-email", exception.getMessage());
    }

    // Test: Null Email
    @Test
    void testSendEmailWithNullEmail() {
        String subject = "Test Subject";
        String text = "Test Body";

        Exception exception = assertThrows(CompletionException.class, () -> {
            emailService.sendEmail(null, subject, text).join();
        });
        assertEquals("java.lang.IllegalArgumentException: Invalid email address: null", exception.getMessage());
    }

    // Test: Empty Subject
    @Test
    void testSendEmailWithEmptySubject() {
        String to = "test@example.com";
        String text = "Test Body";

        Exception exception = assertThrows(CompletionException.class, () -> {
            emailService.sendEmail(to, "", text).join();
        });
        assertEquals("java.lang.IllegalArgumentException: Subject cannot be null or empty", exception.getMessage());
    }

    // Test: Null Text
    @Test
    void testSendEmailWithNullText() {
        String to = "test@example.com";
        String subject = "Test Subject";

        Exception exception = assertThrows(CompletionException.class, () -> {
            emailService.sendEmail(to, subject, null).join();
        });
        assertEquals("java.lang.IllegalArgumentException: Text cannot be null or empty", exception.getMessage());
    }

    // Test: Authentication Failure
    @Test
    void testSendEmailWithAuthenticationFailure() throws MessagingException {
        // Mock MimeMessage
        MimeMessage mimeMessage = mock(MimeMessage.class);
        when(javaMailSender.createMimeMessage()).thenReturn(mimeMessage);

        // Simulate authentication failure
        doThrow(new MailAuthenticationException("Invalid credentials"))
                .when(javaMailSender).send(any(MimeMessage.class));

        String to = "test@example.com";
        String subject = "Test Subject";
        String text = "Test Body";

        Exception exception = assertThrows(CompletionException.class, () -> {
            emailService.sendEmail(to, subject, text).join();
        });

        assertEquals("org.springframework.mail.MailAuthenticationException: Invalid credentials", exception.getMessage());
        assertTrue(exception.getCause() instanceof MailAuthenticationException);
    }

    // Test: SMTP Failure
    @Test
    void testSendEmailWithSMTPFailure() throws MessagingException {
        // Mock MimeMessage
        MimeMessage mimeMessage = mock(MimeMessage.class);
        when(javaMailSender.createMimeMessage()).thenReturn(mimeMessage);

        // Simulate SMTP failure
        doThrow(new MailSendException("SMTP server not reachable"))
                .when(javaMailSender).send(any(MimeMessage.class));

        String to = "test@example.com";
        String subject = "Test Subject";
        String text = "Test Body";

        Exception exception = assertThrows(CompletionException.class, () -> {
            emailService.sendEmail(to, subject, text).join();
        });

        assertEquals("org.springframework.mail.MailSendException: SMTP server not reachable", exception.getMessage());
        assertTrue(exception.getCause() instanceof MailSendException);
    }

    // Test: Email Content Parse Failure
    @Test
    void testSendEmailWithParseFailure() throws MessagingException {
        // Mock MimeMessage
        MimeMessage mimeMessage = mock(MimeMessage.class);
        when(javaMailSender.createMimeMessage()).thenReturn(mimeMessage);

        // Simulate parse failure
        doThrow(new MailParseException("Invalid email content"))
                .when(javaMailSender).send(any(MimeMessage.class));

        String to = "test@example.com";
        String subject = "Test Subject";
        String text = "Test Body";

        Exception exception = assertThrows(CompletionException.class, () -> {
            emailService.sendEmail(to, subject, text).join();
        });

        assertEquals("org.springframework.mail.MailParseException: Invalid email content", exception.getMessage());
        assertTrue(exception.getCause() instanceof MailParseException);
    }
}
