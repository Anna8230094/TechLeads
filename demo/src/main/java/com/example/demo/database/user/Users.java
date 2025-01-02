package com.example.demo.database.user;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String first_name;
    private String last_name;
    private String typeOfIndustry;
    private String email;
    private String hardSkills;
    private String softSkills;
    private String otherTraits;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return first_name;
    }

    public void setFirstName(String first_name) {
        this.first_name = first_name;
    }

    public String getLastName() {
        return last_name;
    }

    public void setLastName(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIndustry() {
        return typeOfIndustry;
    }

    public void setIndustry(String typeOfIndustry) {
        this.typeOfIndustry = typeOfIndustry;
    }

    public void setHardSkills(String hardSkills) {
        this.hardSkills = hardSkills;
    }

    public String getHardSkills() {
        return hardSkills;
    }

    public void setSoftSkills(String softSkills) {
        this.softSkills = softSkills;
    }

    public String getSoftSkills() {
        return softSkills;
    }

    public void setOtherTraits(String otherTraits) {
        this.otherTraits = otherTraits;
    }

    public String getOtherTraits() {
        return otherTraits;
    }
}
