package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.demo.database.user.UserRepository;
import com.example.demo.database.user.Users;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void testSaveAndFindById() {
        // Use the default constructor and setters
        Users user = new Users();
        user.setName("Alice");
        user.setEmail("alice@example.com");
        user.setField("HR");
        user.setHardSkills("Recruitment");
        user.setSoftSkills("Empathy");
        user.setOtherTraits("Fairness");

        Users savedUser = userRepository.save(user);
        Optional<Users> foundUser = userRepository.findById(savedUser.getIdUsers());

        assertTrue(foundUser.isPresent());
        assertEquals("Alice", foundUser.get().getName());
    }

    public void save(Users user) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }
}
