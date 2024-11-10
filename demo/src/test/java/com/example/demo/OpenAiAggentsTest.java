package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;

public class OpenAiAggentsTest {

    
    private final OpenAiAggents openAiAggents = new OpenAiAggents();
   

    @Test
    void loadKeyTest() {
        assertNotNull(openAiAggents.loadKey(), "The open api key is not set");
    }


}
