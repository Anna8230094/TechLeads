
package com.example.demo.openai.service;

import java.util.concurrent.CompletableFuture;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.example.demo.openai.agents.Extractor;
import com.example.demo.openai.agents.ExtractorResearcher;
import com.example.demo.openai.agents.Register;
import com.example.demo.openai.threads.ExtractorThread;
import com.example.demo.openai.threads.OpenAiThread;

@Service
public class OpenAiService {

    @Async
    public CompletableFuture<String> registerResponse(String messageRegister) throws Exception {

        Register register = new Register(Register.MODEL, Register.INSTRUCTIONS, Register.NAME);
        OpenAiThread openAiThread = new OpenAiThread(messageRegister, Register.INSTRUCTIONS, register.getAssistantId());

        CompletableFuture<String> message = openAiThread.addMessage();
        CompletableFuture.allOf(message).join();

        CompletableFuture<String> run = openAiThread.run();
        CompletableFuture.allOf(run).join();
        System.out.println(run.get());

        String response = openAiThread.getRequest();

        return CompletableFuture.completedFuture(response);
    }

    @Async
    public CompletableFuture<String> ExtractorResponse(String messageExtractor) throws Exception {

        Extractor extractor = new Extractor(Extractor.MODEL, Extractor.INSTRUCTIONS, Extractor.NAME);
        ExtractorThread extractorThread = new ExtractorThread(messageExtractor, Extractor.INSTRUCTIONS,
                extractor.getAssistantId());

        CompletableFuture<String > file = extractorThread.uploadFile();
        CompletableFuture.allOf(file).join();


        CompletableFuture<String > message =extractorThread.addMessage();
        CompletableFuture.allOf(message).join();
        
        CompletableFuture<String> run = extractorThread.run();
        String response = extractorThread.getRequest();
        CompletableFuture.allOf(run).join();

        System.out.println(run.get());

        return CompletableFuture.completedFuture(response);
    }

    @Async
    public CompletableFuture<String> extractorResearcherResponse(String researcherExtractot) throws Exception {

        ExtractorResearcher extractorResearcher = new ExtractorResearcher(ExtractorResearcher.MODEL, ExtractorResearcher.INSTRUCTIONS, ExtractorResearcher.NAME);
        OpenAiThread openAiThread = new OpenAiThread(researcherExtractot, ExtractorResearcher.INSTRUCTIONS, extractorResearcher.getAssistantId());

        CompletableFuture<String> message = openAiThread.addMessage();
        CompletableFuture.allOf(message).join();

        CompletableFuture<String> run = openAiThread.run();
        CompletableFuture.allOf(run).join();
        System.out.println(run.get());

        String response = openAiThread.getRequest();

        return CompletableFuture.completedFuture(response);
    }
}
