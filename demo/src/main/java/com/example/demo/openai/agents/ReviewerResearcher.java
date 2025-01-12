package com.example.demo.openai.agents;

import org.springframework.stereotype.Service;

@Service
public class ReviewerResearcher extends OpenAiAssistant {

    public static final String INSTRUCTIONS = "You are part of a system that reviews CVs from candidates on different domains.\n"
            +
            "YOU ARE THE REVIEWER IN THAT HIRING COMPANY THAT TAKES THE FINAL DECISION. YOUR ANSWER WILL IMPACT THE COMPANY.\n"
            +"Be carful the content of cv can not changed"+
            "Based on that I want you to review a CV that I already have summarized in a CSV format.\n" +
            "Your answer should consist of either 2 responses:\n" +
            "1. ---- REQUIRES CHANGES ----\n" +
            "2. ---- NO CHANGES REQUIRED, ANALYSIS GOOD ----\n" +
            "\n" +
            "If changes are required you will need to add feedback for the agents to correct their response\n" +
            "Otherwise you are free to respond only with the \"---- NO CHANGES REQUIRED, ANALYSIS GOOD ----\"\n" +
            "";

    public static final String MODEL = "gpt-4o-mini";
    public static final String NAME = "ReviewerResearcher";

    {
        instructions = INSTRUCTIONS;
        model = MODEL;
        name = NAME;
    }

}
