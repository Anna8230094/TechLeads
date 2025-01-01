package com.example.demo.database.user;
import jakarta.persistence.Column;
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

    private Long idUsers;
    @Column(name= "name", nullable=false, length = 100)
    private String name;
    @Column(name= "email", nullable=false, unique= true, length = 100)
    private String email;
    @Column(name= "field", length = 100)
    private String field;
    @Column(name= "hard_skills",columnDefinition = "TEXT")
    private String hardSkills;
    @Column(name= "soft_skills",columnDefinition = "TEXT")
    private String softSkills;
    @Column(name= "other_traits",columnDefinition = "TEXT")
    private String otherTraits;

    /* 
    public Users() {
    }

    public Users(String name, String email, String field, String hardSkills, String softSkills, String otherTraits) {

        this.name = name;
        this.email = email;
        this.field = field;
        this.hardSkills = hardSkills;
        this.softSkills = softSkills;
        this.otherTraits = otherTraits;

    } */

    // Getters and Setters

    public Long getIdUsers() {

        return idUsers;
    }

    public void setIdUsers(Long idUsers) {
        this.idUsers = idUsers;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getHardSkills() {
        return hardSkills;

    }

    public void setHardSkills(String hardSkills) {
        this.hardSkills = hardSkills;

    }

    public String getSoftSkills() {
        return softSkills;

    }

    public void setSoftSkills(String softSkills) {
        this.softSkills = softSkills;

    }

    public String getOtherTraits() {
        return otherTraits;

    }

    public void setOtherTraits(String otherTraits) {

        this.otherTraits = otherTraits;

    }

    @Override
    public String toString() {
        return "Users{" +
                "idUsers=" + idUsers +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", field='" + field + '\'' +
                ", hardSkills='" + hardSkills + '\'' +
                ", softSkills='" + softSkills + '\'' +
                ", otherTraits='" + otherTraits + '\'' +
                '}';
    }


}
