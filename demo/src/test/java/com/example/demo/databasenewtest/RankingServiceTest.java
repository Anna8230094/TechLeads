package com.example.demo.databasenewtest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.demo.database.ranking.RankingRepository;
import com.example.demo.database.ranking.RankingResult;
import com.example.demo.database.ranking.RankingService;

import static org.mockito.Mockito.*;

import java.util.List;

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
        rankingResult.setSessionId("session123");

        // Mock the repository's save method to return the same object
        when(rankingRepository.save(rankingResult)).thenReturn(rankingResult);

        // Call the service method
        rankingService.saveRankingResult(rankingResult);

        // Verify that the repository's save method was called exactly once
        verify(rankingRepository, times(1)).save(rankingResult);
    }

    @Test
    void testGetAllRanking() {
        // Mock the repository's findAll method
        when(rankingRepository.findAll()).thenReturn(List.of(
            new RankingResult("resume1",  "summary1"),
            new RankingResult("resume2","summary2")
        ));

        // Call the service method
        List<RankingResult> results = rankingService.getAllranking();

        // Verify and assert results
        assertNotNull(results); // Ensure the result list is not null
        assertEquals(2, results.size()); // Verify the number of results

        // Verify details of the first result
        assertEquals("resume1", results.get(0).getResume());
        assertEquals("session123", results.get(0).getSessionId());
        assertEquals("summary1", results.get(0).getResumeSummary());

        // Verify details of the second result
        assertEquals("resume2", results.get(1).getResume());
        assertEquals("session456", results.get(1).getSessionId());
        assertEquals("summary2", results.get(1).getResumeSummary());

        // Verify that the repository's findAll method was called exactly once
        verify(rankingRepository, times(1)).findAll();
    }
}
