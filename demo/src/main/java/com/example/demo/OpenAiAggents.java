package com.example.demo;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

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

    public OpenAiAggents(String model, String instructions, String name){
        this.model = model;
        this.instructions = instructions;
        this.name = name;
    }

    // contructor with all private fields
    public OpenAiAggents(String model, String message, String instructions, String name) {
        this.model = model;
        this.message = message;
        this.instructions = instructions;
        this.name = name;
    }

    // creatAssostant
    public void createOpenAssistant() throws IOException {

        String jsonRequest = creatJsonForAssistant();
        Response response = sendCreateAssistantRequest(jsonRequest);

        if (response != null && response.isSuccessful()) {
            assistantId = extractAssistantId(response);
            if (assistantId != null) {
                setAssistantId(assistantId);
                System.out.println("Assistant created successfully. ID: " + getAssistantId());
            } else {
                System.out.println("Failed to extract assistant ID.");
            }
        } else {
            System.out.println("The creation of assistant is unable");
        }

    }

    // creating json object
    public String creatJsonForAssistant() {

        JSONObject jsonRequest = new JSONObject();
        jsonRequest.put("instructions", getInstructions());
        jsonRequest.put("name", getName());
        jsonRequest.put("model", getModel());
        jsonRequest.put("tools", new JSONArray().put(new JSONObject().put("type", "code_interpreter")));

        return jsonRequest.toString();
    }

    // send request to assistant API
    public Response sendCreateAssistantRequest(String jsonRequest) throws IOException {

        OkHttpClient client = new OkHttpClient().newBuilder().build();
        MediaType mediaType = MediaType.parse("application/json");

        @SuppressWarnings("deprecation")
        RequestBody body = RequestBody.create(mediaType, jsonRequest);

        Request request = new Request.Builder()
                .url("https://api.openai.com/v1/assistants")
                .post(body)
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", key)
                .addHeader("OpenAI-Beta", "assistants=v2")
                .build();

        return client.newCall(request).execute();
    }

    // extract the id of assistant from response body
    public String extractAssistantId(Response response) throws JSONException, IOException {
        JSONObject jsonResponse = new JSONObject(response.body().string());
        return jsonResponse.getString("id");
    }

    // The method that load key from application.properties
    public String loadKey() {
        Properties properties = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("application.properties")) {
            properties.load(input);
            return properties.getProperty("openai.api.key");
        } catch (IOException e) {
            System.err.print(e);
            return null;
        }
    }

    // some getters and setters for private fields
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
