package com.example.demo;

import java.io.File;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.openai.OpenAiThread;
import com.example.demo.openai.agents.Extractor;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);

	}

	@Override
	public void run(String... args) throws Exception {
		
		File file = Extractor.getFile();
		Extractor extractor = new Extractor(Extractor.MODEL, Extractor.INSTRUCTIONS + file, Extractor.NAME);
		String message = "The file is: ";
		OpenAiThread extractorTread = new OpenAiThread(message +file, Extractor.INSTRUCTIONS, extractor.getAssistantId());
		extractorTread.addMessage();
		extractorTread.run();
		String extractorResponse = extractorTread.getRequest();
		
	}

}
