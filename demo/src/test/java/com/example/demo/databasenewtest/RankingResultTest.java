package com.example.demo.databasenewtest;

import com.example.demo.database.ranking.RankingResult;
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

        // Test setters and getters
        rankingResult.setIdRanking(1L);
        assertEquals(1L, rankingResult.getIdRanking());

        rankingResult.setResume("resume example");
        assertEquals("resume example", rankingResult.getResume());

        rankingResult.setResumeSummary("summary resume example");
        assertEquals("summary resume example", rankingResult.getResumeSummary());

    }

    @Test
    void testParameterizedConstructor() {
        // Test parameterized constructor
        RankingResult rankingResult = new RankingResult("resume example", "summary resume example");
        assertNull(rankingResult.getIdRanking()); // ID should still be null as it's generated
        assertEquals("resume example", rankingResult.getResume());
        assertEquals("summary resume example", rankingResult.getResumeSummary());
    
    }

    @Test
    void testNullValues() {
        RankingResult rankingResult = new RankingResult();
        rankingResult.setResume(null);
        assertNull(rankingResult.getResume());

        rankingResult.setResumeSummary(null);
        assertNull(rankingResult.getResumeSummary());
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
