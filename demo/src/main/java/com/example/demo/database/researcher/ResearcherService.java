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
    private  ResearcherRepository researcherRepository ;

    @Async
    public CompletableFuture<Void> saveResearcherResult(ResearcherResult researcherResult) {
        researcherRepository.save(researcherResult);
        System.out.println("The cv context is saved");
        return CompletableFuture.completedFuture(null);
    }

    public List<ResearcherResult> getAllresearcher() {
        return researcherRepository.findAll();
    }

}