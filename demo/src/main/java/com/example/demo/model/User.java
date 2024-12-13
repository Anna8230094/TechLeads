package com.example.demo.model;

public class User {
    private String name;
    private String field;
    private String email;
    private String hardSkills;
    private String softSkills;
    private String otherTraits;
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getField() {
        return field;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
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
