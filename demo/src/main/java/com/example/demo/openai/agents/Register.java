package com.example.demo.openai.agents;

import org.springframework.stereotype.Service;

@Service
public class Register extends OpenAiAssistant {

    public static final String INSTRUCTIONS = "You are responsible for a procedure of cv ranking where other agents are part of as well. Your role is to receive a job description and turn it in csv format (return it in text form)";
    public static final String MODEL = "gpt-4o-mini";
    public static final String NAME = "Register";

    {
        instructions = INSTRUCTIONS;
        model = MODEL;
        name = NAME;
    }

}
