package com.example.demo.database.reasearcher;

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
    private Long id_researcher;
    private String resume;

    public ResearcherResult(){
        this.resume = resume;
    }

    // Getters and Setters
    public Long getIdResearcher() {
        return id_researcher;
    }

    public void setIdResearcher(Long id_researcher) {
        this.id_researcher = id_researcher;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }
}
