package com.example.demo.openAiClasses;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

import org.json.JSONObject;
import org.junit.jupiter.api.AfterAll;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;

import com.example.demo.openai.agents.OpenAiAssistant;
import com.example.demo.openai.agents.Register;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@TestInstance(Lifecycle.PER_CLASS)
public class OpenAiAssistantTest {

    @InjectMocks
    private static Register openAiAssistant;

    @Mock
    private okhttp3.Response mockResponse;;

    @BeforeAll
    static void setUp() throws IOException {
        // MockitoAnnotations.openMocks(this);
        openAiAssistant = new Register();
        // ReflectionTestUtils.setField(openAiAssistant, "instructions", instructions);
        // ReflectionTestUtils.setField(openAiAssistant, "name", name);
    }

    // @Test
    // void createAssistantTest() throws IOException {

    // Request mockRequest = new Request.Builder()
    // .url("https://some-url.com")
    // .build();
    // Response res = new Response.Builder()
    // .request(mockRequest)
    // .protocol(Protocol.HTTP_2)
    // .code(200) // status code
    // .message("")
    // .body(ResponseBody.create(
    // "{\"id\":\"assistant-id-123\"}",
    // MediaType.get("application/json; charset=utf-8")))
    // .build();

    // when(openAiAssistant.sendRequest(anyString(), anyString())).thenReturn(res);
    // // when(openAiAssistant.extractId()).thenReturn(res);

    // // assertEquals(openAiAssistant.getModel(), model);
    // // assertEquals(openAiAssistant.getInstructions(), instructions);
    // assertEquals(openAiAssistant.getName(), "Register");
    // CompletableFuture<String> future = openAiAssistant.createAiAssistant();
    // assertEquals("assistant-id-123", future.join());
    // }

    @Test
    void testCreateAiAssistant() throws IOException {
        // Simulate successful response
        when(mockResponse.isSuccessful()).thenReturn(true);
        String mockResponseBody = "{\"id\":\"assistant-id-123\"}";
        MediaType mediaType = MediaType.parse("application/json");
        ResponseBody responseBody = ResponseBody.create(mockResponseBody, mediaType);
        when(mockResponse.body()).thenReturn(responseBody);

        assertEquals(openAiAssistant.loadKey(), "${OPENAI_API_KEY}");
        assertEquals(openAiAssistant.getName(), "Register");
        CompletableFuture<String> response = openAiAssistant.createAiAssistant();
        assertEquals("assistant-id-123", response.join());
    }

    @Test
    void loadKeyTest() {
        assertNotNull(openAiAssistant.loadKey(), "The key must not be null");
    }

    @Test
    void testBuildJsonForAssistant() {
        // Generate JSON and verify its structure
        String json = openAiAssistant.buildJsonForAssistant();
        JSONObject jsonObject = new JSONObject(json);

        assertEquals("gpt-4o-mini", jsonObject.getString("model"));
        assertEquals(
                "You are responsible for a procedure of cv ranking where other agents are part of as well. Your role is to receive a job description and turn it in csv format (return it in text form)",
                jsonObject.getString("instructions"));
        assertEquals("Register", jsonObject.getString("name"));
        assertTrue(!jsonObject.has("tools"));
    }

    @Test
    void testSendRequestSuccess() throws IOException {
        // Mocking Response
        when(mockResponse.isSuccessful()).thenReturn(true);

        // Create a sample JSON request
        String jsonRequest = openAiAssistant.buildJsonForAssistant();
        String url = "https://api.openai.com/v1/assistants";

        // Mock the method behavior
        Response response = openAiAssistant.sendRequest(jsonRequest, url);

        assertNotNull(response);
    }

    @Test
    void testExtractId() throws IOException {
        // Simulate a response body
        String mockResponseBody = "{\"id\":\"assistant-id-123\"}";
        MediaType mediaType = MediaType.parse("application/json");
        ResponseBody responseBody = ResponseBody.create(mockResponseBody, mediaType);

        when(mockResponse.body()).thenReturn(responseBody);

        String id = openAiAssistant.extractId(mockResponse);

        assertEquals("assistant-id-123", id);
    }

    @AfterAll
    void deleteAssistant() throws IOException {
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
