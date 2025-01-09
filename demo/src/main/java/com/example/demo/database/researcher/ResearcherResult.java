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

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

@Entity
@Table(name = "researcher_result")
public class ResearcherResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idResearcher")
    private Long idResearcher;

    @Column(name = "resume", nullable = false, columnDefinition = "TEXT")
    @Lob
    private String resume;

    @Column(name = "fileName", nullable = false)
    private String fileName;

    

    // Getters and Setters
    public Long getIdResearcher() {
        return idResearcher;
    }

    public void setIdResearcher(Long idResearcher) {
        this.idResearcher = idResearcher;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
