/**
 * This class represents my class in Java.
 * 
 * <p>It is designed to demonstrate how to declare the author of a class
 * using a JavaDoc comment.</p>
 * 
 * @author Konstantia Stergiou
 * @version 1.0
 */

package com.example.demo.database.ranking;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "ranking_result")
public class RankingResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @PrimaryKeyJoinColumn
    private Long idRanking;

    @Column(name = "fileName", nullable = false)
    private String resumeName;

    @Column(name = "sessionId", nullable = false)
    private String sessionId;

    @Column(name = "summary", nullable = false, columnDefinition = "TEXT")
    @Lob
    private String summaryOfResume;

    // Constructors
    public RankingResult() {

    }

    public RankingResult(String resumeName, String summaryOfResume) {

        this.resumeName = resumeName;
        this.summaryOfResume = summaryOfResume;
    }

    // Getters and Setters

    public Long getIdRanking() {
        return idRanking;
    }

    public void setIdRanking(Long idRanking) {
        this.idRanking = idRanking;
    }

    public String getResume() {
        return resumeName;
    }

    public void setResume(String resumeName) {
        this.resumeName = resumeName;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getResumeSummary() {
        return summaryOfResume;
    }

    public void setResumeSummary(String summaryOfResume) {
        this.summaryOfResume = summaryOfResume;
    }

    @Override
    public String toString() {
        return "RankingResult {\n" +
                "    idRanking = " + getIdRanking() + ",\n" +
                "    resumeName = '" + getResume() + "',\n" +
                "    sessionId = '" + getSessionId() + "',\n" +
                "    summaryOfResume = '" + getResumeSummary() + "'\n" +
                '}';
    }

}
