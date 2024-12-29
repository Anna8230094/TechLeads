package com.example.demo;

import org.junit.jupiter.api.Test;

import com.example.demo.database.user.Users;

import static org.junit.jupiter.api.Assertions.*;

public class UsersTest {

    @Test
    public void testDefaultConstructor() {
        Users user = new Users();

        assertNotNull(user, "The user object should not be null.");
    }

    @Test
    public void testParameterizedConstructor() {
        Users user = new Users("John Doe", "john.doe@example.com", "Engineering",
                "Java, Python", "Teamwork, Communication", "Detail-oriented");

        assertEquals("John Doe", user.getName(), "Name should be initialized correctly.");
        assertEquals("john.doe@example.com", user.getEmail(), "Email should be initialized correctly.");
        assertEquals("Engineering", user.getField(), "Field should be initialized correctly.");
        assertEquals("Java, Python", user.getHardSkills(), "Hard skills should be initialized correctly.");
        assertEquals("Teamwork, Communication", user.getSoftSkills(), "Soft skills should be initialized correctly.");
        assertEquals("Detail-oriented", user.getOtherTraits(), "Other traits should be initialized correctly.");
    }

    @Test
    public void testSettersAndGetters() {
        Users user = new Users();

        user.setIdUsers(1L);
        user.setName("Jane Doe");
        user.setEmail("jane.doe@example.com");
        user.setField("Marketing");
        user.setHardSkills("SEO, Content Writing");
        user.setSoftSkills("Creativity, Adaptability");
        user.setOtherTraits("Proactive");

        assertEquals(1L, user.getIdUsers(), "ID should be set correctly.");
        assertEquals("Jane Doe", user.getName(), "Name should be set correctly.");
        assertEquals("jane.doe@example.com", user.getEmail(), "Email should be set correctly.");
        assertEquals("Marketing", user.getField(), "Field should be set correctly.");
        assertEquals("SEO, Content Writing", user.getHardSkills(), "Hard skills should be set correctly.");
        assertEquals("Creativity, Adaptability", user.getSoftSkills(), "Soft skills should be set correctly.");
        assertEquals("Proactive", user.getOtherTraits(), "Other traits should be set correctly.");
    }
}
