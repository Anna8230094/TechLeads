package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.openai.OpenAiThread;
import com.example.demo.openai.agents.Register;



@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);

	}

	@Override
	public void run(String... args) throws Exception {

		// Creation of register assistant
		Register register = new Register(Register.MODEL, Register.INSTRUCTIONS, Register.NAME);
		String message ="Here are the details provided by the user:\nIndustry: Tech\nRole: Software Engineer\nProficiency Level: Mid-Level\nRelated Qualification: Python";
		OpenAiThread regisThread = new OpenAiThread(message, Register.INSTRUCTIONS, register.getAssistantId());
		regisThread.addMessage();
		regisThread.run();

		//This respons will be used as an input in other agents
		String registerResponse = regisThread.getRequest();

	}

}
