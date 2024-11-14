package com.example.demo;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class OpenAiAgentsTest {

    private OpenAiAgents openAiAgents;

    @BeforeEach
    void setUp() {
        openAiAgents = new OpenAiAgents("gpt-4o-mini", "Tell me hi in german", "You are a german translator",
                "Translator");
    }

    @Test
    void loadKeyTest() {
        OpenAiAgents openAiAgent = new OpenAiAgents();
        String apiKey = openAiAgent.getKey();
        assertNotNull(apiKey, "API Key should be loaded and not null.");
    }

    @Test
    void extractIdTest() throws IOException {
        String JsonResponse = "{\"id\":\"test-assistant-id\"}";

        @SuppressWarnings("deprecation")
        Response response = new Response.Builder()
                .code(200)
                .message("OK")
                .protocol(Protocol.HTTP_1_1)
                .request(new Request.Builder().url("https://api.openai.com/v1/assistants").build())
                .body(ResponseBody.create(MediaType.parse("application/json"), JsonResponse))
                .build();

        String extractedId = openAiAgents.extractId(response);
        assertEquals("test-assistant-id", extractedId);
    }

    @Test
    void sendRequestTest() throws IOException {
        String jsonRequest = openAiAgents.buildJsonForAssistant();
        Response response = openAiAgents.sendRequest(jsonRequest, "https://api.openai.com/v1/assistants");
        assertNotNull(response, "Response should not be null.");
    }

    @Test
    void createAssistantTest() throws IOException {
        openAiAgents.createAgent();
        assertNotNull(openAiAgents.getAssistantId(), "Assistant ID should not be null after creation.");
    }

    @Test
    void buildThreadTest() throws IOException {
        openAiAgents.createThread();
        String threadId = openAiAgents.getThreadId();
        assertNotNull(threadId, "Thread ID should not be null after creation.");
        System.out.println("Thread created successfully. ID: " + threadId);
    }

    @Test
    void testAddMessage() throws IOException {
        openAiAgents.createAgent();
        assertNotNull(openAiAgents.getAssistantId(), "Assistant ID should not be null after creation.");

        openAiAgents.createThread();
        assertNotNull(openAiAgents.getThreadId(), "Thread ID should not be null after creation.");

        openAiAgents.addMessage();
        System.out.println("Message added successfully to thread ID: " + openAiAgents.getThreadId());
    }

    @Test
    void testRun() throws IOException {
        openAiAgents.createAgent();
        assertNotNull(openAiAgents.getAssistantId(), "Assistant ID should not be null after creation.");

        openAiAgents.createThread();
        assertNotNull(openAiAgents.getThreadId(), "Thread ID should not be null after creation.");

        openAiAgents.addMessage();

        openAiAgents.run();
        System.out.println("Run method executed successfully for thread ID: " + openAiAgents.getThreadId());
    }

    @Test
    void testGetRequest() throws IOException {
        openAiAgents.createAgent();
        assertNotNull(openAiAgents.getAssistantId(), "Assistant ID should not be null after creation.");

        openAiAgents.createThread();
        assertNotNull(openAiAgents.getThreadId(), "Thread ID should not be null after creation.");

        openAiAgents.addMessage();

        openAiAgents.run();

        openAiAgents.getRequest();
        System.out.println("getRequest method executed successfully for thread ID: " + openAiAgents.getThreadId());

    }

}
