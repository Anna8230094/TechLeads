package com.example.demo;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.demo.user.User;
import com.example.demo.user.UserRepository;

@SpringBootApplication

public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean 
	CommandLineRunner runner(UserRepository repository) {
		return args -> {

      User user = new User();
      user.setFirstName("George");
	  user.setLastName("Papadopoulos");
	  user.setEmail("george.papadopoulos@example.com");
	  user.setTypeOfIndustry("Finance");

      repository.save(user);
      User saved = repository.findById(user.getId()).orElse(null);

	  if (saved != null) {
		  System.out.println("Saved User: " + saved);
	  } else {
		  System.out.println("User not found.");
	  }
	  	
	};
  }
}
