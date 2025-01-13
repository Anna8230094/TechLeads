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

    @Column(name = "fileName", nullable = false)
    private String fileName;

    @Column(name = "resumeName", nullable = false, columnDefinition = "TEXT")
    @Lob
    private String resume;

    @Column(name = "sessionId", nullable = false)
    private String sessionId;

    // Constructors

    public ResearcherResult() {

    }

    public ResearcherResult(String fileName, String resume) {

        this.fileName = fileName;
        this.resume = resume;

    }

    // Getters and Setters
    public Long getIdResearcher() {
        return idResearcher;
    }

    public void setIdResearcher(Long idResearcher) {
        this.idResearcher = idResearcher;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resumeName) {
        this.resume = resumeName;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    @Override
    public String toString() {
        return "ResearcherResult {\n" +
                "    idResearcher = " + getIdResearcher() + ",\n" +
                "    fileName = '" + getFileName() + "'\n" +
                "    resumeName = '" + getResume() + "',\n" +
                "    sessionId = '" + getSessionId() + "',\n" +

                '}';
    }

}
