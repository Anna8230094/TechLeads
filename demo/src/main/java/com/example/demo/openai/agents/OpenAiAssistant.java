package com.example.demo.openai.agents;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.CompletableFuture;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * This class represents my class in Java.
 * @author Anna Maria Megalou
 * @version 1.0
 */

@Service
public class OpenAiAssistant {

    protected String model;
    protected String instructions;
    protected String name;
    private String assistantId;
    
    @Value("${openai.api.key}")
    private String key;


    // creatAssistant
    @Async
    public CompletableFuture <String> createAiAssistant() throws IOException {
        String jsonRequest = buildJsonForAssistant();
        Response response = sendRequest(jsonRequest, "https://api.openai.com/v1/assistants");

        if (response != null && response.isSuccessful()) {
            assistantId = extractId(response);
            System.out.println("Assistant created successfully. ID: " + getAssistantId());
            return CompletableFuture.completedFuture(assistantId);
        } else {
            System.out.println("The creation of assistant is unable");
            throw new IOException();
        }
    }

    // creating json object
    public String buildJsonForAssistant() {
        JSONObject jsonRequest = new JSONObject();
        jsonRequest.put("instructions", getInstructions());
        jsonRequest.put("name", getName());
        jsonRequest.put("model", getModel());
        if (getModel().equals("gpt-4o")) {
            jsonRequest.put("tools", new JSONArray().put(new JSONObject().put("type", "file_search")));
        }
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
                .addHeader("Authorization", "Bearer " + key)
                .addHeader("OpenAI-Beta", "assistants=v2")
                .build();

        return client.newCall(request).execute();
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

    // extract the id from JsonObject
    public String extractId(Response response) throws JSONException, IOException {
        JSONObject jsonResponse = new JSONObject(response.body().string());
        return jsonResponse.getString("id");
    }

    public String getAssistantId() {
        return assistantId;
    }

    public String getInstructions() {
        return instructions;
    }

    public String getName() {
        return name;
    }

    public String getModel() {
        return model;
    }
    public void setKey(String key) {
        this.key = key;
    }
    public String getKey() {
        return key;
    }
}
