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

public final  class OpenAiAssistant {

    private String model;
    private String instructions;
    private String name;
    private String assistantId;
    private final String key = loadKey();

    public OpenAiAssistant() {

    }

    // contructor with all private fields
    public OpenAiAssistant(String model, String instructions, String name) throws IOException {
        this.model = model;
        this.instructions = instructions;
        this.name = name;
        this.assistantId = createAiAssistant();
    }

    // creatAssistant
    public String createAiAssistant() throws IOException {

        String jsonRequest = buildJsonForAssistant();
        Response response = sendRequest(jsonRequest, "https://api.openai.com/v1/assistants");

        if (response != null && response.isSuccessful()) {
            assistantId = extractId(response);
            System.out.println("Assistant created successfully. ID: " + getAssistantId());
            return assistantId;
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
}
