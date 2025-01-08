package com.example.demo.databasetest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.demo.database.researcher.ResearcherRepository;
import com.example.demo.database.researcher.ResearcherResult;
import com.example.demo.database.researcher.ResearcherService;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class ResearcherServiceTest {

    @Mock
    private ResearcherRepository researcherRepository; // Mocking the repository

    @InjectMocks
    private ResearcherService researcherService; // Injecting the mocked repository into the service

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Initialize mocks before each test
    }

    @Test
    void testSaveResearcherResult() {
        // Create a new ResearcherResult instance
        ResearcherResult researcherResult = new ResearcherResult();
        researcherResult.setResume("resume example 1");

        // Mock the repository's save method to return the same object
        when(researcherRepository.save(researcherResult)).thenReturn(researcherResult);

        // Call the service method
        ResearcherResult savedResearcherResult = researcherService.saveResearcherResult(researcherResult);

        // Verify and assert results
        assertNotNull(savedResearcherResult); // Ensure the result is not null
        assertEquals("resume example 1", savedResearcherResult.getResume()); // Verify the value of 'resume'

        // Verify that the repository's save method was called exactly once
        verify(researcherRepository, times(1)).save(researcherResult);
    }
}
