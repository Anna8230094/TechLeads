
package com.example.demo.database.ranking;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.Table;



@Entity
@Table(name = "RankingResult")

public class RankingResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_ranking;
/* 
    @JoinColumn(name = "id_researcher")
    private ResearcherResult researcherResult; */

    private String resume;
    private String summaryOfResume;

    
    /* 
    public RankingResult() {
    }

    public RankingResult(String resume, String summaryOfResume) {
        this.resume = resume;
        this.summaryOfResume = summaryOfResume;
        
        
    } */

    // Getters and Setters

    public Long getIdRanking() {
        return id_ranking;
    }

    public void setIdRanking(Long id_ranking) {
        this.id_ranking= id_ranking;
    }
    
    public String getResume() {
        return resume ;
    }

    public void setResume (String resume) {
        this.resume = resume;
    }


public String getResumeSummary() {
        return summaryOfResume ;
    }

    public void setSummaryResume (String summaryOfResume) {
        this.summaryOfResume = summaryOfResume;
    }

}












