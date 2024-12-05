package com.example.demo.openai.agents;

import java.io.IOException;

public class ReviewerResearcher extends OpenAiAssistant {

    public static final String INSTRUCTIONS = "You are responsible for a cv ranking procedure where other agents are part of as well."
            + "Your role is to receive the Researcher’s result and review it as well as estimate the results and decide whether or not"
            + "they are correct or they need further corrections. 1. Check if Researcher’s results are correct."
            + "If they are, return “they are correct”. 2. If results are not correct, suggest corrections. ";

    public static final String MODEL = "gpt-4o-mini";
    public static final String NAME = "ReviewerResearcher";

    public ReviewerResearcher(String model, String instructions, String name) throws IOException {
        super(model, instructions, name);
    }

}
