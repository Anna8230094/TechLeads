package com.example.demo.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApplicantsService {

    @Autowired
    private UserRepository userRepository;

    public Applicants saveApplicants(Applicants applicants) {
        return userRepository.save(applicants);
    }
}
