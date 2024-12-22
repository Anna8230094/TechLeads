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
	public OpenAiService openAIService;
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		// Step 1:create register
		String messageRegiser = "Here are the details provided by the user:Industry: Tech,Role: Software Engineer,Proficiency Level: Mid-Level,Related Qualification: Python";
		CompletableFuture<String> registerResponse = openAIService.registerResponse(messageRegiser);

		// step 2:create extractor
		String messageExtractor = "The pdf is that i want from you to extract informations is the following: ";
		CompletableFuture<String> extractorResponse = openAIService.ExtractorResponse(messageExtractor);

		CompletableFuture.allOf(registerResponse, extractorResponse).join();
		System.out.println("Register Response: " + registerResponse.get());
		System.out.println("Extractor Response: " + extractorResponse.get());

		// step 3:Create extractorResearcher
		String extractorResearcherMessage = "The resume csv is:" + extractorResponse.get()
				+ "/n The job position in csv is:" + registerResponse.get();
		CompletableFuture<String> extractorResearcherResponse = openAIService
				.extractorResearcherResponse(extractorResearcherMessage);
		CompletableFuture.allOf(extractorResearcherResponse).join();
		System.out.println("ExtractorResearcher response is :" + extractorResearcherResponse.get());

		System.out.println("ExtractorResearcher response is :" + extractorResearcherResponse.get());

		// step 4:Create reviewer reasearcher
		String messageReviewer = "The response of extraxtorReasearcher is: " + extractorResearcherResponse.get();
		CompletableFuture<String> reviewerResponse = openAIService.reviewerResponse(messageReviewer);
		CompletableFuture.allOf(reviewerResponse).join();

		String response = reviewerResponse.get();

		// step 5:check the ansswer of rewier
		int count = 0;
		while (count < 5) {
			if (!response.contains("---- NO CHANGES REQUIRED, ANALYSIS GOOD ----")) {
				System.out.println("THE REVIEWER SYGGEST CORRECTIONS");
				String systemMessage = reviewerResponse.get();
				CompletableFuture<String> extarctorResearcherCorrections = openAIService
						.extractorResearcherResponse(systemMessage);
				CompletableFuture.allOf(extarctorResearcherCorrections).join();

				messageReviewer = "The response of extraxtorReasearcher is: "
						+ extarctorResearcherCorrections.get();
				reviewerResponse = openAIService.reviewerResponse(messageReviewer);
				CompletableFuture.allOf(reviewerResponse).join();

				response = reviewerResponse.get();
				count++;
				System.out.println(response.toString());
			} else {
				break;
			}
		}
		// researcherResult.setResume(extractorResearcherMessage);
		// researcherService.saveResearcherResult(researcherResult);
		// System.out.println(researcherRepository.findAll());

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
