package com.example.demo.database;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResearcherService {
    @Autowired
    private ResearcherRepository researcherRepository;

    public ResearcherResult saveResearcherResult(ResearcherResult researcherResult) {
        return researcherRepository.save(researcherResult);
    }
}


