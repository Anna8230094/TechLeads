package com.example.demo.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.demo.database.reasearcher.ResearcherResult;
import com.example.demo.database.reasearcher.ResearcherService;
import com.example.demo.database.user.Users;
import com.example.demo.database.user.UsersService;

@Component
public class DatabseComponent implements CommandLineRunner {

    @Autowired
    UsersService usersService;

    @Autowired
    ResearcherService researcerService;

    @Override
    public void run(String... args) {

        Users user1 = new Users();
        user1.setFirstName("Olibia");
        user1.setLastName("Megalou");
        user1.setEmail("oly.meg@example.com");
        user1.setIndustry("Finance");

        Users user2 = new Users();
        user2.setFirstName("Maria");
        user2.setLastName("Iliadi");
        user2.setEmail("maria.ili@example.com");
        user2.setIndustry("Human Resources");

        Users user3 = new Users();
        user3.setFirstName("Alex");
        user3.setLastName("Papadopoulos");
        user3.setEmail("alex.pap@example.com");
        user3.setIndustry("Marketing and Communication");

        Users user4 = new Users();
        user4.setFirstName("Andrew");
        user4.setLastName("Papandreou");
        user4.setEmail("an.papandreou@example.com");
        user4.setIndustry("Architecture");

        usersService.saveUsers(user1);
        usersService.saveUsers(user2);
        usersService.saveUsers(user3);
        usersService.saveUsers(user4);

        researcerService.saveResearcherResult(new ResearcherResult("mmm", null));
    }

}
