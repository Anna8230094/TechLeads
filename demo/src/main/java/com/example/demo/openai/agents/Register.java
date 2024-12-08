package com.example.demo.openai.agents;

import java.io.IOException;

public class Register extends OpenAiAssistant {

    public static final String INSTRUCTIONS = "You are responsible for a procedure of cv ranking where other agents are part of as well. Your role is to receive a job description and turn it in csv format (return it in text form)";
    public static final String MODEL = "gpt-4o-mini";
    public static final String NAME = "Register";

    public Register(String model, String instructions, String name) throws IOException {
        super(model, instructions, name);
    }

}
