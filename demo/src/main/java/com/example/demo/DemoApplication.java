package com.example.demo;

import java.util.Scanner;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import com.example.demo.mail.EmailService;
import com.example.demo.openai.service.OpenAiService;


@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	@Autowired
	public static OpenAiService openAIService = new OpenAiService();
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);

	}

	@Override
	public void run(String... args) throws Exception {


		String messageRegiser = "Here are the details provided by the user:Industry: Tech,Role: Software Engineer,Proficiency Level: Mid-Level,Related Qualification: Python";
		CompletableFuture<String> registerResponse = openAIService.registerResponse(messageRegiser);

		String messageExtractor = "Convert to me  the pdf in csv format not a tablr or file";
		CompletableFuture<String>extractorResponse = openAIService.ExtractorResponse(messageExtractor);

		CompletableFuture.allOf(registerResponse, extractorResponse).join();

		System.out.println("Register Response: " + registerResponse.get());
        System.out.println("Extractor Response: " + extractorResponse.get());
	}
	
	@Bean
    @SuppressWarnings("ConvertToTryWithResources")
	public CommandLineRunner commandLineRunner(ApplicationContext context) {
		return args -> {
			Scanner scanner = new Scanner(System.in);
			EmailService emailService = context.getBean(EmailService.class);

			System.out.println("Enter the subject of the email:");
			String subject = scanner.nextLine();

			System.out.println("Enter the body of the email:");
			String body = scanner.nextLine();

			String to = "annamegalou3@gmail.com ";
			emailService.sendEmail(to, subject, body);

			System.out.println("Email sent successfully to " + to + "!");
			scanner.close();
		};

	}
	

}
