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
		applicant.setFirstName("OLY");
		applicant.setLastName("MEGALOU");
		applicant.setEmail("oly.meg@example.com");
		applicant.setIndustry("Finance");
		applicantsService.saveApplicants(applicant);

	}
}
