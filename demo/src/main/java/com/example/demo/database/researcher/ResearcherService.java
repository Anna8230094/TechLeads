/**
 * This class represents my class in Java.
 * 
 * <p>It is designed to demonstrate how to declare the author of a class
 * using a JavaDoc comment.</p>
 * 
 * @author Konstantia Stergiou
 * @version 1.0
 */
package com.example.demo.database.researcher;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResearcherService {

    @Autowired
    private ResearcherRepository researcherRepository;

    public void saveResearcherResult(ResearcherResult researcherResult) {
        researcherRepository.save(researcherResult);

    }

    public List<Map<Long, String>> getResearcherResumesById(ResearcherResult researcherResult) {
        return researcherRepository.findMapResume(researcherResult.getIdResearcher());
    }

    
}