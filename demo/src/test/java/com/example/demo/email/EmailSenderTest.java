package com.example.demo.email;


import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import com.example.demo.mail.EmailSender;

import java.util.Properties;
/**
 * This class represents my class in Java.
 * 
 * @author Aggeliki Despoina Megalou
 * @version 1.0
 */

public class EmailSenderTest {

    private EmailSender emailSender;

    @BeforeEach
    public void setup() {
        emailSender = new EmailSender();
    }

    @Test
    public void testGetJavaMailSender_Configuration() {
        JavaMailSender mailSender = emailSender.getJavaMailSender();
        assertNotNull(mailSender);
        assertTrue(mailSender instanceof JavaMailSenderImpl);

        JavaMailSenderImpl impl = (JavaMailSenderImpl) mailSender;

        // Assert host and port
        assertEquals("smtp.gmail.com", impl.getHost());
        assertEquals(587, impl.getPort());

        // Assert username and password
        assertEquals("my.gmail@gmail.com", impl.getUsername());
        assertEquals("password", impl.getPassword());

        // Assert properties
        Properties props = impl.getJavaMailProperties();
        assertEquals("smtp", props.getProperty("mail.transport.protocol"));
        assertEquals("true", props.getProperty("mail.smtp.auth"));
        assertEquals("true", props.getProperty("mail.smtp.starttls.enable"));
        assertEquals("true", props.getProperty("mail.debug"));
    }
}
