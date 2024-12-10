package com.example.demo;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.example.demo.mail.EmailService;
import com.example.demo.openai.service.OpenAiService;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	@Autowired
	public OpenAiService openAIService;

	@Autowired
	public EmailService emailService;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		String messageRegiser = "Here are the details provided by the user:Industry: Tech,Role: Software Engineer,Proficiency Level: Mid-Level,Related Qualification: Python";
		CompletableFuture<String> registerResponse = openAIService.registerResponse(messageRegiser);

		String messageExtractor = "The pdf is that i want from you to extract informations is the following: ";
		CompletableFuture<String> extractorResponse = openAIService.ExtractorResponse(messageExtractor);

		CompletableFuture.allOf(registerResponse, extractorResponse).join();

		System.out.println("Register Response: " + registerResponse.get());
		System.out.println("Extractor Response: " + extractorResponse.get());

		String extractorResearcherMessage = "The resume csv is:" + extractorResponse.get()
				+ "/n The job position in csv is:" + registerResponse.get();
		CompletableFuture<String> extractorResearcherResponse = openAIService
				.extractorResearcherResponse(extractorResearcherMessage);

		CompletableFuture.allOf(extractorResearcherResponse).join();

		System.out.println("ExtractorResearcher response is :" + extractorResearcherResponse.get());

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

}
