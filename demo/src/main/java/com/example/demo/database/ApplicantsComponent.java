package com.example.demo.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class ApplicantsComponent implements CommandLineRunner{
    
    @Autowired
	ApplicantsService applicantsService ;

    @Override
	public void run(String... args) {

		Applicants applicant = new Applicants();
		applicant.setFirstName("Olibia");
		applicant.setFirstName("Maria");
		applicant.setFirstName("Alex");
		applicant.setFirstName("Andrew");
		applicant.setLastName("Megalou");
		applicant.setLastName("Iliadi");
		applicant.setLastName("Papadopoulos");
		applicant.setLastName("Papandreou");
		applicant.setEmail("oly.meg@example.com");
		applicant.setEmail("maria.ili@example.com");
		applicant.setEmail("alex.pap@example.com");
		applicant.setEmail("an.papandreou@example.com");
		applicant.setIndustry("Finance");
		applicant.setIndustry("Human Resources");
		applicant.setIndustry("Marketing and Communication");
		applicant.setIndustry("Architecture");

		applicantsService.saveApplicants(applicant);

	}
}
