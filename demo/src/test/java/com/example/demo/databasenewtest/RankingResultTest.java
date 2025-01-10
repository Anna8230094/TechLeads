package com.example.demo.databasenewtest;

import com.example.demo.database.ranking.RankingResult;
import com.example.demo.database.researcher.ResearcherResult;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RankingResultTest {

    @Test
    void testGettersAndSetters() {
        RankingResult rankingResult = new RankingResult();

        // Test default state
        assertNull(rankingResult.getIdRanking());
        assertNull(rankingResult.getResume());
        assertNull(rankingResult.getResumeSummary());
        assertNull(rankingResult.getResearcherResult());

        // Test setters and getters
        rankingResult.setIdRanking(1L);
        assertEquals(1L, rankingResult.getIdRanking());

        rankingResult.setResume("resume example");
        assertEquals("resume example", rankingResult.getResume());

        rankingResult.setResumeSummary("summary resume example");
        assertEquals("summary resume example", rankingResult.getResumeSummary());

        ResearcherResult researcherResult = new ResearcherResult();
        rankingResult.setRes(researcherResult);
        assertEquals(researcherResult, rankingResult.getResearcherResult());
    }

    @Test
    void testParameterizedConstructor() {
        // Create a mock ResearcherResult object
        ResearcherResult researcherResult = new ResearcherResult();

        // Test parameterized constructor
        RankingResult rankingResult = new RankingResult("resume example", "summary resume example", researcherResult);
        assertNull(rankingResult.getIdRanking()); // ID should still be null as it's generated
        assertEquals("resume example", rankingResult.getResume());
        assertEquals("summary resume example", rankingResult.getResumeSummary());
        assertEquals(researcherResult, rankingResult.getResearcherResult());
    }

    @Test
    void testNullValues() {
        RankingResult rankingResult = new RankingResult();
        rankingResult.setResume(null);
        assertNull(rankingResult.getResume());

        rankingResult.setResumeSummary(null);
        assertNull(rankingResult.getResumeSummary());

        rankingResult.setRes(null);
        assertNull(rankingResult.getResearcherResult());
    }

    @Test
    void testEmptyStringResume() {
        RankingResult rankingResult = new RankingResult();
        rankingResult.setResume("");
        assertEquals("", rankingResult.getResume());

        rankingResult.setResumeSummary("");
        assertEquals("", rankingResult.getResumeSummary());
    }
}
