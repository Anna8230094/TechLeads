package com.example.demo;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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


		
		//step 5:create ranking
		String messageRanking = " The resume from the database are:";
		CompletableFuture<String> rankingResponse = openAIService.reviewerRanking(messageRanking);
		CompletableFuture.allOf(rankingResponse).join();



		//step 6: Create reviewer ranking
		String messageReviewerRanking = "The response of RankingAgent is: [INSERT_RANKING_RESULT]";
		CompletableFuture<String> reviewerRankingResponse = openAIService.reviewerRanking(messageReviewerRanking);
		CompletableFuture.allOf(reviewerRankingResponse).join();

		
	}
	 
 
	 
}

	
	// researcherResult.setResume(extractorResearcherMessage);
	// researcherService.saveResearcherResult(researcherResult);
	// System.out.println(researcherRepository.findAll());



/*
 * @Bean
 * 
 * @SuppressWarnings("ConvertToTryWithResources")
 * public CommandLineRunner commandLineRunner(ApplicationContext context) {
 * return args -> {
 * Scanner scanner = new Scanner(System.in);
 * EmailService emailService = context.getBean(EmailService.class);
 * 
 * System.out.println("Enter the subject of the email:");
 * String subject = scanner.nextLine();
 * 
 * System.out.println("Enter the body of the email:");
 * String body = scanner.nextLine();
 * 
 * String to = "annamegalou3@gmail.com ";
 * emailService.sendEmail(to, subject, body);
 * 
 * System.out.println("Email sent successfully to " + to + "!");
 * scanner.close();
 * };
 * 
 * }
 */
