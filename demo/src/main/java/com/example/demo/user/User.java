package com.example.demo.user;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Id;


@Entity
@Table
public class User {
   
    @Id
    @SequenceGenerator(
        name ="user_sequence",
        sequenceName = "user_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy= GenerationType.SEQUENCE,
        generator = "student_sequence"
    )
    private Long id;
    private String first_name;
    private String last_name;
    private String email;
    private String typeOfIndustry; 

    public User() {
    }

    public User(Long id,String first_name,String last_name, String email,String typeOfIndustry) {
        this.id=id;
        this.first_name=first_name;
        this.last_name=last_name;
        this.email=email;
        this.typeOfIndustry=typeOfIndustry;
    }
    public User(String first_name,String last_name, String email,String typeOfIndustry) {
        this.first_name=first_name;
        this.last_name=last_name;
        this.email=email;
        this.typeOfIndustry=typeOfIndustry;
    }

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

   
    public String getTypeOfIndustry() {
        return typeOfIndustry;
    }

    public void setTypeOfIndustry(String typeOfIndustry) {
        this.typeOfIndustry = typeOfIndustry;
    }
    
    
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ",first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", email='" + email + '\'' +
                ", typeOfIndustry='" + typeOfIndustry + '\'' +
                '}';
    }
   
   
}


