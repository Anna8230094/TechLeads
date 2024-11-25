package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.openai.OpenAiThread;
import com.example.demo.openai.agents.Extractor;
import com.example.demo.openai.agents.Register;
import com.example.demo.openai.threads.ExtractorThread;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);

	}

	@Override
	public void run(String... args) throws Exception {
		
		String messageRegiser = "Here are the details provided by the user:Industry: Tech,Role: Software Engineer,Proficiency Level: Mid-Level,Related Qualification: Python";
		Register register = new Register(Register.MODEL, Register.INSTRUCTIONS, Register.NAME);

		OpenAiThread openAiThread = new OpenAiThread(messageRegiser, Register.INSTRUCTIONS, register.getAssistantId());
				
		openAiThread.createThread();
		openAiThread.addMessage();
		openAiThread.run();
		String registerResponse = openAiThread.getRequest();


		Extractor extractor = new Extractor(Extractor.MODEL, Extractor.INSTRUCTIONS, Extractor.NAME);

		ExtractorThread extractorThread = new ExtractorThread(registerResponse, Extractor.INSTRUCTIONS, extractor.getAssistantId());
		extractorThread.uploadFile();		
		extractorThread.createThread();
		extractorThread.addMessage();
		extractorThread.run();
		extractorThread.getRequest();


	}

}
