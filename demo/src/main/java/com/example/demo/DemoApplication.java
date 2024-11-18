package com.example.demo;

import java.util.Scanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
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
