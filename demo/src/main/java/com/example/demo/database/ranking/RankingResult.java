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

import com.example.demo.database.researcher.ResearcherResult;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "ranking_result")

public class RankingResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_ranking;

    @ManyToOne
    @JoinColumn(name = "IdResearcher", nullable = false)
    private ResearcherResult researcherResult;

    private String resume;
    private String summaryOfResume;

    // Getters and Setters

    public Long getIdRanking() {
        return id_ranking;
    }

    public void setIdRanking(Long id_ranking) {
        this.id_ranking = id_ranking;
    }

    public ResearcherResult getResearcherResult() {
        return researcherResult;
    }

    public void setRes(ResearcherResult researcherResult) {
        this.researcherResult = researcherResult;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public String getResumeSummary() {
        return summaryOfResume;
    }

    public void setSummaryResume(String summaryOfResume) {
        this.summaryOfResume = summaryOfResume;
    }

}
