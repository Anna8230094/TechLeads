package com.example.demo.database.researcher;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "ResearcherResult")
public class ResearcherResult {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idResearcher")
    private Long idResearcher;

    @Column(name = "resume", nullable = false)
    private String resume;

    /*public ResearcherResult() {
        
    }

    public ResearcherResult(String resume){
        this.resume = resume;
    }*/

    
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
}
