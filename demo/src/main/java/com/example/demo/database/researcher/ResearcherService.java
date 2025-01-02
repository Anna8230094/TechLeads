package com.example.demo.database.researcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service

public class ResearcherService {

    @Autowired
    private ResearcherRepository ResearcherRepository;
        public ResearcherResult saveResearcherResult(ResearcherResult researcherResult) {
    
            return ResearcherRepository.save(researcherResult);

    }

}