package com.example.demo;

import java.io.IOException;

import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OpenAiAgentsTest {

    private static  OpenAiAgent openAiAgents;
    private static  OpenAiAssistant openAiAssistant;
    String assistantId;


    @BeforeEach
    void setUp() throws IOException {
        openAiAssistant = new OpenAiAssistant("gpt-4o-mini", "You are a german translator", "Translator");
        assistantId = openAiAssistant.getAssistantId();
        openAiAgents = new OpenAiAgent( "Tell me hi in german", "You are a german translator", assistantId);
    }

    @Test
    void loadKeyTest() {
        String apiKey = openAiAgents.getKey();
        assertNotNull(apiKey, "API Key should be loaded and not null.");
    }

    @Test
    void buildThreadTest() throws IOException {
        assertNotNull(openAiAgents.createThread(), "Thread ID should not be null after creation.");
        System.out.println("Thread created successfully. ID: " + openAiAgents.getThreadId());
    }

    @Test
    void testAddMessage() throws IOException {
        openAiAgents.getThreadId();
        assertNotNull(openAiAgents.getThreadId(), "Thread ID should not be null after creation.");

        openAiAgents.addMessage();
        System.out.println("Message added successfully to thread ID: " + openAiAgents.getThreadId());
    }

    @Test
    void testRun() throws IOException {
    
        assertNotNull(openAiAssistant.getAssistantId(), "Assistant ID should not be null after creation.");
        assertNotNull(openAiAgents.getThreadId(), "Thread ID should not be null after creation.");

        openAiAgents.addMessage();

        openAiAgents.run();
        System.out.println("Run method executed successfully for thread ID: " + openAiAgents.getThreadId());
    }

    @Test
    void testGetRequest() throws IOException {
    
        assertNotNull(openAiAssistant.getAssistantId());
        assertNotNull(openAiAgents.getThreadId(), "Thread ID should not be null after creation.");

        openAiAgents.addMessage();
        openAiAgents.run();

        openAiAgents.getRequest();
        System.out.println("getRequest method executed successfully for thread ID: " + openAiAgents.getThreadId());

    }

    @AfterAll
    @SuppressWarnings("unused")
    static void deleteAssistants() throws IOException{
         OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://api.openai.com/v1/assistants/" + openAiAssistant.getAssistantId())
                .delete()
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Bearer " + openAiAssistant.loadKey())
                .addHeader("OpenAI-Beta", "assistants=v2")
                .build();
                
        Response response = client.newCall(request).execute();
        if (!response.isSuccessful())
            System.out.println("The delete of assistant is unable");
    }

}
