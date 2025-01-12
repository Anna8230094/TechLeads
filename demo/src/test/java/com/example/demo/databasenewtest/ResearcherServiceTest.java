package com.example.demo.databasenewtest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.demo.database.researcher.ResearcherRepository;
import com.example.demo.database.researcher.ResearcherResult;
import com.example.demo.database.researcher.ResearcherService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

import java.util.concurrent.CompletableFuture;


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
        researcherResult.setFileName("cv.pdf");

        // Mock the repository's save method to return the same object
        when(researcherRepository.save(researcherResult)).thenReturn(researcherResult);

        // Call the service method asynchronously
        CompletableFuture<Void> result = researcherService.saveResearcherResult(researcherResult);

        // Wait for the result to complete without throwing an exception
        result.join();  // join() blocks until the async operation completes

        // Verify and assert results
        assertNotNull(researcherResult); // Ensure the result is not null
        assertEquals("resume example 1", researcherResult.getResume()); // Verify the value of 'resume'
        assertEquals("cv.pdf", researcherResult.getFileName()); // Verify the value of 'resume summary'




        // Verify that the repository's save method was called exactly once
        verify(researcherRepository, times(1)).save(researcherResult);
    }

}
