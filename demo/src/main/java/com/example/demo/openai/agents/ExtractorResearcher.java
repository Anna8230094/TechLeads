package com.example.demo.openai.agents;

import org.springframework.stereotype.Service;

@Service
public class ExtractorResearcher extends OpenAiAssistant {
    
    public static final String INSTRUCTIONS = "You are responsible for a cv ranking procedure where other agents are part of as well. Your role is to receive a job description(in a csv format) as well as a cv (in csv format) and based on that I want you to review a CV and return a summary in a CSV format. ";
    public static final String MODEL = "gpt-4o-mini";
    public static final String NAME = "ExtractorResearcher";

    {
        instructions = INSTRUCTIONS;
        model = MODEL;
        name = NAME;
    }


}
