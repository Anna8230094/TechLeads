package com.example.demo.openai.agents;

import java.io.IOException;

import com.example.demo.openai.OpenAiAssistant;

public class Extractor extends OpenAiAssistant{
    
    public Extractor(String model, String instructions, String name) throws IOException {
        super(model, instructions, name);
    }
    
}
