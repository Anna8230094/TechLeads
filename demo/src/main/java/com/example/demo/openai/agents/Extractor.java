package com.example.demo.openai.agents;

import java.io.IOException;

public class Extractor extends OpenAiAssistant {

    public static final String INSTRUCTIONS = "You are responsible for a  cv ranking procedure where other agents are part of as well. Your role is to extract the text included in the cv and turn it into a csv format(return it in text form)";
    public static final String MODEL = "gpt-4o";
    public static final String NAME = "Extractor";

    public Extractor(String model, String instructions, String name) throws IOException {
        super(model, instructions, name);
    }
}
