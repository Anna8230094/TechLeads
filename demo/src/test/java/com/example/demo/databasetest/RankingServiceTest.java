package com.example.demo.databasetest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.demo.database.ranking.RankingRepository;
import com.example.demo.database.ranking.RankingResult;
import com.example.demo.database.ranking.RankingService;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class RankingServiceTest {

    @Mock
    private RankingRepository rankingRepository; // Mocking the repository

    @InjectMocks
    private RankingService rankingService; // Injecting the mocked repository into the service

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Initialize mocks before each test
    }

    @Test
    void testSaveRankingResult() {
        // Create a new RankingResult instance
        RankingResult rankingResult = new RankingResult();
        rankingResult.setResume("resume example 1");
        rankingResult.setResumeSummary("summary example");

        // Mock the repository's save method to return the same object
        when(rankingRepository.save(rankingResult)).thenReturn(rankingResult);

        // Call the service method
        RankingResult savedRankingResult = rankingService.saveRankingResult(rankingResult);

        // Verify and assert results
        assertNotNull(savedRankingResult); // Ensure the result is not null
        assertEquals("resume example 1", savedRankingResult.getResume()); // Verify the value of 'resume'
        assertEquals("summary example", savedRankingResult.getResumeSummary()); // Verify the value of 'resume summary'

        // Verify that the repository's save method was called exactly once
        verify(rankingRepository, times(1)).save(rankingResult);
    }
}
