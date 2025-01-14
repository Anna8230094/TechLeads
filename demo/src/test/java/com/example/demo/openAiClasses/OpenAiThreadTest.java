package com.example.demo.openaiclasses;
/*
 * Copyright [2024-2025] [TechLeads]
 *
 * Licensed under multiple licenses:
 * 1. Apache License, Version 2.0 (the «Apache License»);
 *    You may obtain a copy of the Apache License at:
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * 2. MIT License (the «MIT License»);
 *    You may obtain a copy of the MIT License at:
 *        https://opensource.org/licenses/MIT
 *
 * 3. Eclipse Public License 2.0 (the «EPL 2.0»);
 *    You may obtain a copy of the EPL 2.0 at:
 *        https://www.eclipse.org/legal/epl-2.0/
 *
 * You may not use this file except in compliance with one or more of these licenses.
 * Unless required by applicable law or agreed to in writing, software distributed
 * under these licenses is distributed on an «AS IS» BASIS, WITHOUT WARRANTIES
 * OR CONDITIONS OF ANY KIND, either express or implied.
 * See the applicable licenses for the specific language governing permissions and
 * limitations under those licenses.
 */
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
