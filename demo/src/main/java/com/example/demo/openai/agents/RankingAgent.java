package com.example.demo.openai.agents;

import java.io.IOException;

public class RankingAgent extends OpenAiAssistant {

    public static final String INSTRUCTIONS = "You are responsible for a cv ranking procedure where other agents are part of as well. Your role is to receive a list of resumes in a csv format and return the applicant’s resumes ranked from most suitable to least suitable.";
    public static final String MODEL = "gpt-4o-mini";
    public static final String NAME = "Ranking Applicants";

    public RankingAgent(String model, String instructions, String name) throws IOException {
        super(model, instructions, name);
    }

}
