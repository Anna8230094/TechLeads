package com.example.demo.openai.agents;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class RankingAgent extends OpenAiAssistant {

    public static final String INSTRUCTIONS = "You are responsible for a cv ranking procedure where other agents are part of as well. Your role is to receive a list of resumes in a csv format and return the applicant’s resumes ranked from most suitable to least suitable.";
    public static final String MODEL = "gpt-4o-mini";
    public static final String NAME = "Ranking Applicants";

    {
        instructions = INSTRUCTIONS;
        model = MODEL;
        name = NAME;
    }

}
