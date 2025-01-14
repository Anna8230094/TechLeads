package com.example.demo.databaseTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.demo.database.researcher.ResearcherRepository;
import com.example.demo.database.researcher.ResearcherResult;
import com.example.demo.database.researcher.ResearcherService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;


class ResearcherServiceTest {

    @Mock
    private ResearcherRepository researcherRepository; 

    @InjectMocks
    private ResearcherService researcherService; 
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); 
    }

    @Test
    void testSaveResearcherResult() {
        // Create a new ResearcherResult instance
        ResearcherResult researcherResult = new ResearcherResult("cv.pdf", "resume example 1");

        researcherResult.setSessionId("session123");
        // Mock the repository's save method to return the same object
        when(researcherRepository.save(researcherResult)).thenReturn(researcherResult);

        // Call the service method
        researcherService.saveResearcherResult(researcherResult);

        // Verify that the repository's save method was called exactly once
        verify(researcherRepository, times(1)).save(researcherResult);

        // Assert the variables of ResearcherResult
        assertNotNull(researcherResult);
        assertEquals("resume example 1", researcherResult.getResume());
        assertEquals("cv.pdf", researcherResult.getFileName());
        assertEquals("session123", researcherResult.getSessionId());
    }

    @Test
    void testGetAllResearcher() {
        // Create mock data
        ResearcherResult researcherResult1 = new ResearcherResult("cv1.pdf", "resume1");
        ResearcherResult researcherResult2 = new ResearcherResult("cv2.pdf", "resume2");

        List<ResearcherResult> mockResults = List.of(researcherResult1, researcherResult2);

        // Mock the repository method
        when(researcherRepository.findBySessionId("session123")).thenReturn(mockResults);

        // Call the service method
        List<ResearcherResult> results = researcherService.getAllresearcher("session123");

        // Verify the repository call and assert results
        verify(researcherRepository, times(1)).findBySessionId("session123");
        assertNotNull(results);
        assertEquals(2, results.size());
        assertEquals("resume1", results.get(0).getResume());
        assertEquals("resume2", results.get(1).getResume());
    }

   
}
