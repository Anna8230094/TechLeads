
package com.example.demo.openai.service;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.example.demo.openai.agents.Extractor;
import com.example.demo.openai.agents.ExtractorResearcher;
import com.example.demo.openai.agents.Register;
import com.example.demo.openai.threads.ExtractorThread;
import com.example.demo.openai.threads.OpenAiThread;

@Service
public class OpenAiService {

    @Autowired
    public Register register;
    @Autowired
    public Extractor extractor;
    @Autowired
    public ExtractorResearcher extractorResearcher;

    @Autowired
    @Qualifier(value = "OpenAiThread")
    public OpenAiThread registerOpenAiThread;
    @Autowired
    @Qualifier(value = "ExtractorThread")
    public ExtractorThread extractorOpenAiThread;
    @Autowired
    @Qualifier(value = "OpenAiThread")
    public OpenAiThread extractorResearcherOpenAiThread;

    @Async
    public CompletableFuture<String> registerResponse(String messageRegister) throws Exception {

        CompletableFuture<String> create = register.createAiAssistant();
        CompletableFuture<String> thread = registerOpenAiThread.createThread(messageRegister, Register.INSTRUCTIONS,
                register.getAssistantId());

        CompletableFuture.allOf(create).join();
        CompletableFuture.allOf(thread).join();

        CompletableFuture<String> message = registerOpenAiThread.addMessage();
        CompletableFuture.allOf(message).join();

        CompletableFuture<String> run = registerOpenAiThread.run();
        CompletableFuture.allOf(run).join();
        System.out.println(run.get());

        String response = registerOpenAiThread.getRequest();

        return CompletableFuture.completedFuture(response);
    }

    @Async
    public CompletableFuture<String> ExtractorResponse(String messageExtractor) throws Exception {

        extractor.createAiAssistant();
        extractorOpenAiThread.createThread(messageExtractor, Extractor.INSTRUCTIONS,
                extractor.getAssistantId());

        CompletableFuture<String> file = extractorOpenAiThread.uploadFile();
        CompletableFuture.allOf(file).join();

        CompletableFuture<String> message = extractorOpenAiThread.addMessage();
        CompletableFuture.allOf(message).join();

        CompletableFuture<String> run = extractorOpenAiThread.run();
        String response = extractorOpenAiThread.getRequest();
        CompletableFuture.allOf(run).join();

        System.out.println(run.get());

        return CompletableFuture.completedFuture(response);
    }

    @Async
    public CompletableFuture<String> extractorResearcherResponse(String researcherExtractot) throws Exception {

        extractorResearcher.createAiAssistant();
        registerOpenAiThread.createThread(researcherExtractot, ExtractorResearcher.INSTRUCTIONS,
                extractorResearcher.getAssistantId());

        CompletableFuture<String> message = registerOpenAiThread.addMessage();
        CompletableFuture.allOf(message).join();

        CompletableFuture<String> run = registerOpenAiThread.run();
        CompletableFuture.allOf(run).join();
        System.out.println(run.get());

        String response = registerOpenAiThread.getRequest();

        return CompletableFuture.completedFuture(response);
    }
}
