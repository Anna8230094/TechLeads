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

import org.springframework.data.jpa.repository.JpaRepository;

public interface ResearcherRepository extends JpaRepository<ResearcherResult, Long> {
    
        // This method will automatically generate a query to fetch results by researcher ID
        List<ResearcherResult> findByIdResearcher(Long researcherId);
}
