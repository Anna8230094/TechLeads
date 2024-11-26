package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.demo.Applicants;
import com.example.demo.ApplicantsService;

@Component
public class ApplicantsComponent implements CommandLineRunner{
    
    @Autowired
	ApplicantsService applicantsService ;

    @Override
	public void run(String... args) {

		Applicants applicant = new Applicants();
		applicant.setName("John Doe");
		applicant.setEmail("john.doe@example.com");
		applicantsService.saveApplicants(applicant);

	}
}
