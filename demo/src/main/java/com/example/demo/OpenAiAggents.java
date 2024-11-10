package com.example.demo;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class OpenAiAggents {

    private String model;
    private String message;
    private String instructions;
    private String name;
    private String assistantId = "";
    private final String key = loadKey();

    // an empty constructor for testing key
    public OpenAiAggents() {
    }

    // contructor
    public OpenAiAggents(String model, String message, String instructions, String name) {
        this.model = model;
        this.message = message;
        this.instructions = instructions;
        this.name = name;
    }

    // creatAssostant
    public void createOpenAssistant() throws IOException {

    }

    // send request to assistant API
    public void sendCreateAssistantRequest() {

    }

    //The method that load key from application.properties
    public String loadKey() {
        Properties properties = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("application.properties")) {
            properties.load(input);
            return properties.getProperty("openai.api.key");
        } catch (IOException ex) {
            System.err.print(ex);
            return null;
        }
    }

    //some getters and setters for private fields
    public String getModel() {
        return this.model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getInstructions() {
        return this.instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAssistantId() {
        return assistantId;
    }

    public void setAssistantId(String assistantId) {
        this.assistantId = assistantId;
    }

    public String getKey() {
        return key;
    }

}
