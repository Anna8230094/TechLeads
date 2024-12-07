
package com.example.demo.openai.service;

import java.util.concurrent.CompletableFuture;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.example.demo.openai.agents.Extractor;
import com.example.demo.openai.agents.RankingAgent;
import com.example.demo.openai.agents.Register;
import com.example.demo.openai.threads.ExtractorThread;
import com.example.demo.openai.threads.OpenAiThread;

@Service
public class OpenAiService {

    @Async
    public CompletableFuture<String> registerResponse(String messageRegister) throws Exception {

        Register register = new Register(Register.MODEL, Register.INSTRUCTIONS, Register.NAME);
        OpenAiThread openAiThread = new OpenAiThread(messageRegister, Register.INSTRUCTIONS, register.getAssistantId());
        // i add the message in thread
        openAiThread.addMessage();

        // i execute async the run method
        CompletableFuture<String> run = openAiThread.run();
        CompletableFuture.allOf(run).join();
        System.out.println(run.get());

        // i get the response
        String response = openAiThread.getRequest();

        return CompletableFuture.completedFuture(response);
    }

    @Async
    public CompletableFuture<String> ExtractorResponse(String messageExtractor) throws Exception {

        Extractor extractor = new Extractor(Extractor.MODEL, Extractor.INSTRUCTIONS, Extractor.NAME);
        ExtractorThread extractorThread = new ExtractorThread(messageExtractor, Extractor.INSTRUCTIONS,
                extractor.getAssistantId());

        CompletableFuture<String> file = extractorThread.uploadFile();
        CompletableFuture.allOf(file).join();

        extractorThread.addMessage();

        CompletableFuture<String> run = extractorThread.run();
        String response = extractorThread.getRequest();
        CompletableFuture.allOf(run).join();

        System.out.println(run.get());

        return CompletableFuture.completedFuture(response);
    }

    @Async
    public CompletableFuture<String> rankingResponse(String messageRanking) throws Exception {

        RankingAgent rankingAgent = new RankingAgent(messageRanking, RankingAgent.INSTRUCTIONS, RankingAgent.NAME);
        OpenAiThread openAiThread = new OpenAiThread(messageRanking, RankingAgent.INSTRUCTIONS,
                rankingAgent.getAssistantId());
        // i add the message in thread
        openAiThread.addMessage();

        // i execute async the run method
        CompletableFuture<String> run = openAiThread.run();
        CompletableFuture.allOf(run).join();
        System.out.println(run.get());

        // i get the response
        String response = openAiThread.getRequest();

        return CompletableFuture.completedFuture(response);

    }
}
