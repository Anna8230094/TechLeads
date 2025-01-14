package com.example.demo.openaiclasses;
// package com.example.demo.openAiClasses;

// import static org.junit.jupiter.api.Assertions.*;

// import java.io.IOException;

// import org.junit.jupiter.api.AfterAll;
// import org.junit.jupiter.api.BeforeAll;
// import org.junit.jupiter.api.Test;

// import com.example.demo.openai.agents.OpenAiAssistant;
// import com.example.demo.openai.threads.OpenAiThread;

// import okhttp3.OkHttpClient;
// import okhttp3.Request;
// import okhttp3.Response;

// public class OpenAiThreadTest {

//     private static  OpenAiThread openAiThread;
//     private static  OpenAiAssistant openAiAssistant;
//     String assistantId;


//     @BeforeAll
//     void setUp() throws IOException {
//         openAiAssistant = new OpenAiAssistant();
//         assistantId = openAiAssistant.getAssistantId();
//         openAiThread = new OpenAiThread();
//     }

//     @Test
//     void loadKeyTest() {
//         String apiKey = openAiThread.getKey();
//         assertNotNull(apiKey, "API Key should be loaded and not null.");
//     }

//     @Test
//     void buildThreadTest() throws IOException {
//         assertNotNull(openAiThread.createThread( "You are a german translator", assistantId), "Thread ID should not be null after creation.");
//         System.out.println("Thread created successfully. ID: " + openAiThread.getThreadId());
//     }

//     @Test
//     void testAddMessage() throws IOException {
//         openAiThread.getThreadId();
//         assertNotNull(openAiThread.getThreadId(), "Thread ID should not be null after creation.");

//         openAiThread.addMessage("user", "Tell me hi in german");
//         System.out.println("Message added successfully to thread ID: " + openAiThread.getThreadId());
//     }

//     @Test
//     void testRun() throws IOException {
    
//         assertNotNull(openAiAssistant.getAssistantId(), "Assistant ID should not be null after creation.");
//         assertNotNull(openAiThread.getThreadId(), "Thread ID should not be null after creation.");

//         openAiThread.addMessage("user", "Tell me hi in german");

//         openAiThread.run();
//         System.out.println("Run method executed successfully for thread ID: " + openAiThread.getThreadId());
//     }

//     @Test
//     void testGetRequest() throws IOException {
    
//         assertNotNull(openAiAssistant.getAssistantId());
//         assertNotNull(openAiThread.getThreadId(), "Thread ID should not be null after creation.");

//         openAiThread.addMessage("user", "Tell me hi in german");
//         openAiThread.run();

//         openAiThread.getRequest();
//         System.out.println("getRequest method executed successfully for thread ID: " + openAiThread.getThreadId());

//     }

//     @AfterAll
//     static void deleteAssistants() throws IOException{
//          OkHttpClient client = new OkHttpClient();

//         Request request = new Request.Builder()
//                 .url("https://api.openai.com/v1/assistants/" + openAiAssistant.getAssistantId())
//                 .delete()
//                 .addHeader("Content-Type", "application/json")
//                 .addHeader("Authorization", "Bearer " + openAiAssistant.loadKey())
//                 .addHeader("OpenAI-Beta", "assistants=v2")
//                 .build();
                
//         Response response = client.newCall(request).execute();
//         if (!response.isSuccessful())
//             System.out.println("The delete of assistant is unable");
//     }

// }
