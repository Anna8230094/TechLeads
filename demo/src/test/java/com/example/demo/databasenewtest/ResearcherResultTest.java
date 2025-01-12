package com.example.demo.databasenewtest;



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

        researcherResult.setFileName("cv.pdf");
        assertEquals("cv.pdf", researcherResult.getFileName());
    }

    @Test
    void testParameterizedConstructor() {
        // Test parameterized constructor
        ResearcherResult researcherResult = new ResearcherResult("resume example","cv.pdf");
        assertNull(researcherResult.getIdResearcher()); // ID should still be null as it's generated
        assertEquals("resume example", researcherResult.getResume());
        assertEquals("cv.pdf", researcherResult.getFileName());
    }

    @Test
    void testNullValues() {
        ResearcherResult researcherResult = new ResearcherResult();
        researcherResult.setResume(null);
        assertNull(researcherResult.getResume());
        researcherResult.setFileName(null);
        assertNull(researcherResult.getFileName());
    }

    @Test
    void testEmptyStringResume() {
        ResearcherResult researcherResult = new ResearcherResult();
        researcherResult.setResume("");
        assertEquals("", researcherResult.getResume());
        researcherResult.setFileName("");
        assertEquals("", researcherResult.getFileName());
    }
    @Test
    void testToString() {
        ResearcherResult researcherResult = new ResearcherResult();
        researcherResult.setIdResearcher(1L);
        researcherResult.setResume("resume example");
        researcherResult.setFileName("cv.pdf");


        String expected = "ResearcherResult {\n" +
            "    idResearcher = 1,\n" +
            "    resumeName = 'resume example',\n" +  
            "    fileName = 'cv.pdf'\n" + 
            "}";

        assertEquals(expected, researcherResult.toString());

        
    }
}

