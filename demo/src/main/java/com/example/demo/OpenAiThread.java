package com.example.demo;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public final class OpenAiThread {

    private String message;
    private String instructions;
    private String threadId = "";
    private final String key = loadKey();
    private final String assistantId;

    // contructor with all private fields
    public OpenAiThread(String message, String instructions, String assistantId) throws IOException {
        this.message = message;
        this.instructions = instructions;
        this.assistantId = assistantId;
        this.threadId = createThread();
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

    // extract the id from JsonObject
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
    public String createThread() throws IOException {

        String jsonRequest = "";
        Response response = sendRequest(jsonRequest, "https://api.openai.com/v1/threads");

        if (response != null && response.isSuccessful()) {
            threadId = extractId(response);
            System.out.println("Thread id is created successfully. ID: " + getThreadId());
            return threadId;
        } else {
            System.out.println("The creation of thread is unable");
            throw new IOException();
        }
    }

    // Adding message to the assistant
    public void addMessage() throws IOException {

        JSONObject jsonObject = new JSONObject()
                .put("role", "user")
                .put("content", getMessage());

        String jsonRequest = jsonObject.toString();

        Response response = sendRequest(jsonRequest,
                "https://api.openai.com/v1/threads/" + getThreadId() + "/messages");

        if (response.isSuccessful() && response.body() != null) {
            System.out.println("Message add message successfully.");
        } else {
            System.out.println("Failed to add message ");
        }

    }

    public void run() throws IOException {

        JSONObject jsonObject = new JSONObject()
                .put("assistant_id", assistantId)
                .put("instructions", getInstructions());

        String jsonRequest = jsonObject.toString();

        Response response = sendRequest(jsonRequest, "https://api.openai.com/v1/threads/" + getThreadId() + "/runs");

        if (response.isSuccessful() && response.body() != null) {
            System.out.println("Message sent successfully to user.");
        } else {
            System.out.println("Failed to get response ");
        }

    }

    // get Answer from assistant
    public void getRequest() throws IOException {

        OkHttpClient client = new OkHttpClient().newBuilder().build();
        Request request = new Request.Builder()
                .url("https://api.openai.com/v1/threads/" + getThreadId() + "/messages")
                .get()
                .addHeader("Authorization", "Bearer " + key)
                .addHeader("Content-Type", "application/json")
                .addHeader("OpenAI-Beta", "assistants=v2")
                .build();

        Response response = client.newCall(request).execute();

        if (response.isSuccessful() && response.body() != null) {
            String responseBody = response.body().string();
            JSONObject jsonResponse = new JSONObject(responseBody);

            String assistantsResult = jsonResponse.getJSONArray("data").getJSONObject(0)
                    .getJSONArray("content").getJSONObject(0).getJSONObject("text")
                    .getString("value");

            System.out.println(assistantsResult);
        } else {
            System.out.println("Failed to get the result");
        }

    }

    // some getters and setters for private fields
    public String getMessage() {
        return this.message;
    }

    public String getInstructions() {
        return this.instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getThreadId() {
        return threadId;
    }

    public String getKey() {
        return key;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
