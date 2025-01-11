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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
 @Entity
 @Table(name = "ranking_result")
 
 public class RankingResult {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     @PrimaryKeyJoinColumn
     private Long idRanking;
 
     @ManyToOne
     @JoinColumn(name = "IdResearcher", nullable = false)
     private Long researcherResultId;
 
    @Column(name = "fileName", nullable = false, columnDefinition = "TEXT")
    @Lob
     private String resumeName;

     @Column(name = "summary", nullable = false, columnDefinition = "TEXT")
     @Lob
     private String summaryOfResume;
 
     //Constructors
     public RankingResult() {
 
     }
 
     public RankingResult(String resumeName, String summaryOfResume,Long researcherResultId) {
 
         this.resumeName = resumeName;
         this.summaryOfResume = summaryOfResume;
         this.researcherResultId = researcherResultId;
 
     }
 
     // Getters and Setters
 
     public Long getIdRanking() {
         return idRanking;
     }
 
     public void setIdRanking(Long idRanking) {
         this.idRanking= idRanking;
     }
     
 
     public Long getResearcherResultId() {
         return researcherResultId;
     }
 
     public void setResearcherResultId(Long researcherResultId) {
         this.researcherResultId = researcherResultId;
     }
     public String getResume() {
         return resumeName;
     }
 
     public void setResume(String resumeName) {
         this.resumeName = resumeName;
     }
 
 
 public String getResumeSummary() {
         return summaryOfResume ;
     }
 
     public void setResumeSummary (String summaryOfResume) {
         this.summaryOfResume = summaryOfResume;
     }


     @Override
public String toString() {
    return "RankingResult {\n" +
           "    idRanking = " + getIdRanking() + ",\n" +
           "    researcherResultId = " + getResearcherResultId() + ",\n" +
           "    resumeName = '" + getResume() + "',\n" +
           "    summaryOfResume = '" + getResumeSummary() + "'\n" +
           '}';
}

 
 }
 

 
 