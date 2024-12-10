package com.example.demo;

import java.io.IOException;

import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.example.demo.openai.agents.OpenAiAssistant;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OpenAiAssistantTest {

    private static  OpenAiAssistant openAiAssistant;

    @BeforeAll
    static void setUp() throws IOException {
        openAiAssistant = new OpenAiAssistant();
    }

    @Test
    void createAssistantTest() {
        assertNotNull(openAiAssistant.getAssistantId(), "The assistant id must not be null");
    }

    @Test
    void loadKeyTest() {
        assertNotNull(openAiAssistant.loadKey(), "The key must not be null");
    }

    @AfterAll
    static void deleteAssistant() throws IOException {

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
