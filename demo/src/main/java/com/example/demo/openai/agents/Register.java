package com.example.demo.openai.agents;

import java.io.IOException;

public class Register extends OpenAiAssistant {

    public static final String INSTRUCTIONS = "You are part of a system that reviews CVs from candidates on different domains, each job will be specific to a position/role. Based on the registration info I want you to create a structured CSV (not a file) containing all the information, that the following AI agents will need to review the CVs. This will not be for a specific candidate rather more the requirements for the role.";
    public static final String MODEL = "gpt-4o-mini";
    public static final String NAME = "Register";

    public Register(String model, String instructions, String name) throws IOException {
        super(model, instructions, name);
    }

}
