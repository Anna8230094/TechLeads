package com.example.demo.openai.agents;

import org.springframework.stereotype.Service;
@Service
public class ReviewerRanking extends OpenAiAssistant{
    
    public static final String INSTRUCTIONS = "You are part of a system that reviews CVs from candidates on different domains. YOU ARE THE REVIEWER IN THAT HIRING COMPANY THAT TAKES THE FINAL DECISION. YOUR ANSWER WILL IMPACT THE COMPANY."+
                                        "Based on that I want you to review  the RankingAgent’s result and estimate if the ranking of applicants is correct./n"+
                                        "Your answer should consist of either 2 responses:"+
                                        "1. ---- REQUIRES CHANGES ----/n"+
                                        "2. ---- NO CHANGES REQUIRED, ANALYSIS GOOD ----/n"+
                                        "If changes are required you will need to add feedback for the agents to correct their response Otherwise you are free to respond only with the /'---- NO CHANGES REQUIRED, ANALYSIS GOOD ----/'";

    public static final String MODEL = "gpt-4o-mini";
    public static final String NAME = "Reviewer";

    {
        instructions = INSTRUCTIONS;
        model = MODEL;
        name = NAME;
    }

    
}
    