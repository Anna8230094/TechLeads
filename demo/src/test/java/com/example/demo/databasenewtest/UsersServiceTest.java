package com.example.demo.databasenewtest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.demo.database.user.Users;
import com.example.demo.database.user.UsersService;
import com.example.demo.database.user.UserRepository;

import java.util.concurrent.CompletableFuture;

import static org.mockito.Mockito.*;

class UsersServiceTest {

    @Mock
    private UserRepository userRepository; // Mocking the repository

    @InjectMocks
    private UsersService usersService; // Service to be tested

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Initialize mocks
    }

    @Test
    void testSaveUsers() {
        // Create a new user with updated details
        Users user = new Users();
        user.setName("Finovatech Solutions");
        user.setEmail("oly.meg@finovatech.com");
        user.setField("Finance");
        user.setHardSkills("Financial analysis and modeling");
        user.setSoftSkills("Analytical thinking, Attention to detail, Risk management mindset");
        user.setOtherTraits("Integrity, Curiosity, Resilience, Discipline");

        // Mock the repository save method
        when(userRepository.save(user)).thenReturn(user);

        // Call the service method asynchronously
        CompletableFuture<Void> result = usersService.saveUsers(user);

        // Wait for the result to complete without throwing an exception
        result.join();  // join() blocks until the async operation completes

        // Verify the repository's save method was called once
        verify(userRepository, times(1)).save(user);
    }
}
