package com.example.demo;

import org.junit.jupiter.api.Test;

import com.example.demo.database.user.Users;

import static org.junit.jupiter.api.Assertions.*;

public class UsersTests {

    @Test
    void testGettersAndSetters() {
        Users user = new Users();

        user.setIdUsers(1L);
        assertEquals(1L, user.getIdUsers());

        user.setName("John Doe");
        assertEquals("John Doe", user.getName());

        user.setEmail("john.doe@example.com");
        assertEquals("john.doe@example.com", user.getEmail());

        user.setField("Engineering");
        assertEquals("Engineering", user.getField());

        user.setHardSkills("Java, Spring Boot");
        assertEquals("Java, Spring Boot", user.getHardSkills());

        user.setSoftSkills("Teamwork, Communication");
        assertEquals("Teamwork, Communication", user.getSoftSkills());

        user.setOtherTraits("Proactive, Creative");
        assertEquals("Proactive, Creative", user.getOtherTraits());
    }

    @Test
    void testToString() {
        Users user = new Users();
        user.setIdUsers(1L);
        user.setName("John Doe");
        user.setEmail("john.doe@example.com");
        user.setField("Engineering");
        user.setHardSkills("Java, Spring Boot");
        user.setSoftSkills("Teamwork, Communication");
        user.setOtherTraits("Proactive, Creative");

        String expected = "Users{idUsers=1, name='John Doe', email='john.doe@example.com', field='Engineering', hardSkills='Java, Spring Boot', softSkills='Teamwork, Communication', otherTraits='Proactive, Creative'}";
        assertEquals(expected, user.toString());
    }
}