package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class UsersComponent implements CommandLineRunner{
    
    @Autowired
	UsersService usersService ;

    @Override
	public void run(String... args) {

		Users user = new Users();
		user.setFirstName("Olibia");
		user.setFirstName("Maria");
		user.setFirstName("Alex");
		user.setFirstName("Andrew");
		user.setLastName("Megalou");
		user.setLastName("Iliadi");
		user.setLastName("Papadopoulos");
		user.setLastName("Papandreou");
		user.setEmail("oly.meg@example.com");
		user.setEmail("maria.ili@example.com");
		user.setEmail("alex.pap@example.com");
		user.setEmail("an.papandreou@example.com");
		user.setIndustry("Finance");
		user.setIndustry("Human Resources");
		user.setIndustry("Marketing and Communication");
		user.setIndustry("Architecture");

		usersService.saveUsers(user);

	}
} 
