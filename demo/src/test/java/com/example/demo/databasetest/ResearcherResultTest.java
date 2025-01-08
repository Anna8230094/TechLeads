package com.example.demo.databasetest;

import com.example.demo.database.researcher.ResearcherResult;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ResearcherResultTest {

    @Test
    void testGettersAndSetters() {
        ResearcherResult researcherResult = new ResearcherResult();

        // Test default state
        assertNull(researcherResult.getIdResearcher());
        assertNull(researcherResult.getResume());

        // Test setters and getters
        researcherResult.setIdResearcher(1L);
        assertEquals(1L, researcherResult.getIdResearcher());

        researcherResult.setResume("resume example");
        assertEquals("resume example", researcherResult.getResume());
    }

    @Test
    void testParameterizedConstructor() {
        // Test parameterized constructor
        ResearcherResult researcherResult = new ResearcherResult("resume example");
        assertNull(researcherResult.getIdResearcher()); // ID should still be null as it's generated
        assertEquals("resume example", researcherResult.getResume());
    }

    @Test
    void testNullValues() {
        ResearcherResult researcherResult = new ResearcherResult();
        researcherResult.setResume(null);
        assertNull(researcherResult.getResume());
    }

    @Test
    void testEmptyStringResume() {
        ResearcherResult researcherResult = new ResearcherResult();
        researcherResult.setResume("");
        assertEquals("", researcherResult.getResume());
    }
}
