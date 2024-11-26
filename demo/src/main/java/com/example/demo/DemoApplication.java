package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {//implements CommandLineRunner 

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);

	}
/*
	@Override
	public void run(String... args) throws Exception {

		String messageRegiser = "Here are the details provided by the user:Industry: Tech,Role: Software Engineer,Proficiency Level: Mid-Level,Related Qualification: Python";
		Register register = new Register(Register.MODEL, Register.INSTRUCTIONS, Register.NAME);

		OpenAiThread openAiThread = new OpenAiThread(messageRegiser, Register.INSTRUCTIONS, register.getAssistantId());

		openAiThread.createThread();
		openAiThread.addMessage();
		openAiThread.run();
		String registerResponse = openAiThread.getRequest();
		System.out.println(registerResponse);

		Extractor extractor = new Extractor(Extractor.MODEL, Extractor.INSTRUCTIONS, Extractor.NAME);

		String messageExtractor = "Convert to me  the pdf in csv format not a tablr or file";
		ExtractorThread extractorThread = new ExtractorThread(messageExtractor, Extractor.INSTRUCTIONS,
				extractor.getAssistantId());
		extractorThread.uploadFile();
		extractorThread.createThread();
		extractorThread.addMessage();
		extractorThread.run();
		extractorThread.getRequest();


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
*/
}
