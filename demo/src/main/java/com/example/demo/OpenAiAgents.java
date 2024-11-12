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

public class OpenAiAgents {

    private String model;
    private String message;
    private String instructions;
    private String name;
    private String assistantId = "";
    private String threadId = "";
    private final String key = loadKey();

    // an empty constructor for testing key
    public OpenAiAgents() {

    }

    public OpenAiAgents(String model, String instructions, String name) {
        this.model = model;
        this.instructions = instructions;
        this.name = name;
    }

    // contructor with all private fields
    public OpenAiAgents(String model, String message, String instructions, String name) throws IOException {
        this.model = model;
        this.message = message;
        this.instructions = instructions;
        this.name = name;
    }

    // creatAssistant
    public void createAggent() throws IOException {

        String jsonRequest = buildJsonForAssistant();
        Response response = sendRequest(jsonRequest, "https://api.openai.com/v1/assistants");

        if (response != null && response.isSuccessful()) {
            assistantId = extractId(response);
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
    public String buildJsonForAssistant() {

        JSONObject jsonRequest = new JSONObject();
        jsonRequest.put("instructions", getInstructions());
        jsonRequest.put("name", getName());
        jsonRequest.put("model", getModel());
        jsonRequest.put("tools", new JSONArray().put(new JSONObject().put("type", "code_interpreter")));

        return jsonRequest.toString();
    }

    // send request to assistant API
    public Response sendRequest(String jsonRequest, String url) throws IOException {

        OkHttpClient client = new OkHttpClient().newBuilder().build();
        MediaType mediaType = MediaType.parse("application/json");

        @SuppressWarnings("deprecation")
        RequestBody body = RequestBody.create(mediaType, jsonRequest);

        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Bearer " +key)
                .addHeader("OpenAI-Beta", "assistants=v2")
                .build();

        return client.newCall(request).execute();
    }

    // extract the id  from JsonObject
    public String extractId(Response response) throws JSONException, IOException {
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

    // The method that create the Thread
    public void createThread() throws IOException {

        String jsonRequest = "";
        Response response = sendRequest(jsonRequest, "https://api.openai.com/v1/threads");

        //It is possible to create a method that will do that because i use that if else statement a lot of time in my code
        if (response != null && response.isSuccessful()) {
            threadId = extractId(response);
            if (threadId != null) {
                setThreadId(threadId);
                System.out.println("Thread id is created successfully. ID: " +getThreadId());
            } else {
                System.out.println("Failed to extract thread ID.");
            }
        } else {
            System.out.println("The creation of thread is unable");
        }
    }



    // some getters and setters for private fields
    public String getModel() {
        return this.model;
    }

    public String getMessage() {
        return this.message;
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

    public String getAssistantId() {
        return assistantId;
    }

    public void setAssistantId(String assistantId) {
        this.assistantId = assistantId;
    }

    public void setThreadId(String threadId) {
        this.threadId = threadId;
    }

    public String getThreadId() {
        return threadId;
    }

    public String getKey() {
        return key;
    }

}
