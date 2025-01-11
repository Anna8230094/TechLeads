package com.example.demo;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

import com.example.demo.mail.EmailService;

@SpringBootApplication
@EnableAsync
public class DemoApplication implements CommandLineRunner{

/* 
	@Autowired
	public EmailService emailService;
*/
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
/* 
	@Override
	public void run(String... args) throws Exception {
		
		// Send Email Async
		String to = "aggmegalou@gmail.com";
		String subject = " Results Ready!";
		String body = "Your processed data and results are ready. Please check the provided link for further detailss.";

		CompletableFuture<String> emailResponse = emailService.sendEmail(to, subject, body);

		emailResponse.thenAccept(response -> {
			System.out.println("Email sent successfully: " + response);
		}).exceptionally(ex -> {
			System.err.println("Failed to send email: " + ex.getMessage());
			return null;
		});

		CompletableFuture.allOf(emailResponse).join();

	}

	*/
}
