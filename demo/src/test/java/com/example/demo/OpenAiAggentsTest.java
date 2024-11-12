package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;

public class OpenAiAggentsTest {

    
    private final OpenAiAgents openAiAgents = new OpenAiAgents();
   

    @Test
    void loadKeyTest() {
        assertNotNull(openAiAgents.loadKey(), "The open api key is not set");
    }


}
