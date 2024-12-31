package com.example.demo.database.reasearcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResearcherService {
    @Autowired
    private ResearcherRepository researcherRepository;

    public void  saveResearcherResult(ResearcherResult researcherResult) {
         researcherRepository.save(researcherResult);
    }
}
