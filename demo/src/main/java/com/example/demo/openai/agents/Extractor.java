package com.example.demo.openai.agents;

import java.io.IOException;

public class Extractor extends OpenAiAssistant {

    public static final String INSTRUCTIONS = "I want you to extract as much more data you can from the file that i will load to you in a structured CSV forma (NOT A TABLE or A file), this CSV will be later ingested by a other AI Agent that will be reviewing the outcome file";
    public static final String MODEL = "gpt-4o";
    public static final String NAME = "Extractor";

    public Extractor(String model, String instructions, String name) throws IOException {
        super(model, instructions, name);
    }
}
