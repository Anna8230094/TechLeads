package com.example.demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.when;

import com.example.demo.database.user.Users;
import com.example.demo.database.user.UsersService;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class UsersServiceTest {

    @Mock
    private UserRepositoryTest userRepository;

    @InjectMocks
    private UsersService usersService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveUsers() {
        // Use the default constructor and setters
        Users user = new Users();
        user.setName("Alice");
        user.setEmail("alice@example.com");
        user.setField("HR");
        user.setHardSkills("Recruitment");
        user.setSoftSkills("Empathy");
        user.setOtherTraits("Fairness");

        // Mock the save method of the repository
        when(userRepository.save(user)).thenReturn(user);
        
        // Call the service method and assert the results
        Users savedUser = usersService.saveUsers(user);
        
        assertNotNull(savedUser);
        assertEquals("Alice", savedUser.getName());
        
        // Verify the interaction with the repository
        verify(userRepository, times(1)).save(user);
    }
}
