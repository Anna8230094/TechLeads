package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.openai.agents.Extractor;
import com.example.demo.openai.threads.ExtractorThread;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);

	}

	@Override
	public void run(String... args) throws Exception {
		
		Extractor extractor = new Extractor(Extractor.MODEL, Extractor.INSTRUCTIONS, Extractor.NAME);

		String message =" Tell me what this PDF is about";
		ExtractorThread extractorThread = new ExtractorThread(message, Extractor.INSTRUCTIONS, extractor.getAssistantId());
		extractorThread.uploadFile();		
		extractorThread.createThread();
		extractorThread.addMessage();
		extractorThread.run();
		extractorThread.getRequest();


	}

}
