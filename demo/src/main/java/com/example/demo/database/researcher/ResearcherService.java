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
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class ResearcherService {

    @Autowired
    private ResearcherRepository researcherRepository;

    public void saveResearcherResult(ResearcherResult researcherResult) {
        researcherRepository.save(researcherResult);
        System.out.println("Researcher result is saved");
    }

    public List<ResearcherResult> getAllresearcher(String sessionId) {
        return researcherRepository.findBySessionId(sessionId);
    }

    public void deleteResearcherResult(Long id) {
        // Find the researcher result from the primary key id
        ResearcherResult researcherResult = researcherRepository.findById(id).orElse(null);

        if (researcherResult != null) {
            // Set resume , fileName null so the researcher will be deleted
            researcherResult.setResume(null);
            researcherResult.setFileName(null);

            // Save the updated changes
            researcherRepository.save(researcherResult);
        }
    }

}