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
    private Long id_ranking;

    // @ManyToOne
    // @JoinColumn(name = "IdResearcher", nullable = false)
    // private Long researcherResultId;

    @Column(name = "fileName", nullable = false)
    private String resumeName;

    @Column(name = "resume", nullable = false,  columnDefinition = "TEXT")
    @Lob
    private String summaryOfResume;

    // Getters and Setters
    public Long getIdRanking() {
        return id_ranking;
    }

    public String getResume() {
        return resumeName;
    }

    public void setResume(String resumeName) {
        this.resumeName = resumeName;
    }

    public String getResumeSummary() {
        return summaryOfResume;
    }

    public void setSummaryResume(String summaryOfResume) {
        this.summaryOfResume = summaryOfResume;
    }

    // public Long getResearcherResultId() {
    //     return researcherResultId;
    // }

    // public void setResearcherResultId(Long researcherResultId) {
    //     this.researcherResultId = researcherResultId;
    // }

}
