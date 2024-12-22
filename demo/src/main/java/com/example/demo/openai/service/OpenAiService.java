
package com.example.demo.openai.service;

import java.util.concurrent.CompletableFuture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.example.demo.openai.agents.Extractor;
import com.example.demo.openai.agents.ExtractorResearcher;
import com.example.demo.openai.agents.OpenAiAssistant;
import com.example.demo.openai.agents.RankingAgent;
import com.example.demo.openai.agents.Register;
import com.example.demo.openai.agents.ReviewerRanking;
import com.example.demo.openai.agents.ReviewerResearcher;
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
    public ReviewerResearcher reviewerResearcher;
    @Autowired
    public ReviewerRanking reviewerRanking;
    @Autowired
    public RankingAgent rankingAgent;

    @Autowired
    @Qualifier(value = "OpenAiThread")
    public OpenAiThread registerOpenAiThread;
    @Autowired
    @Qualifier(value = "ExtractorThread")
    public ExtractorThread extractorOpenAiThread;
    @Autowired
    @Qualifier(value = "OpenAiThread")
    public OpenAiThread extractorResearcherOpenAiThread;
    @Autowired
    @Qualifier(value = "OpenAiThread")
    public OpenAiThread reviewerResearcherThread;
    @Autowired
    @Qualifier(value = "OpenAiThread")
    public OpenAiThread reviewerRankingThread;
    @Autowired
    @Qualifier(value = "OpenAiThread")
    public OpenAiThread rankingAgentThread;

    @Async
    public CompletableFuture<String> processRequest(String message, String instructions, OpenAiAssistant assistant,
            OpenAiThread thread, boolean uploadFile) throws Exception {
        // Step 1: Create AI Assistant
        CompletableFuture<String> createAssistant = assistant.createAiAssistant();
        CompletableFuture.allOf(createAssistant).join();

        // Step 2: Create Thread
        CompletableFuture<String> createThread = thread.createThread(instructions, assistant.getAssistantId());
        CompletableFuture.allOf(createThread).join();

        // Step 3: Optional File Upload
        if (uploadFile && thread instanceof ExtractorThread) {
            CompletableFuture<String> fileUpload = ((ExtractorThread) thread).uploadFile();
            CompletableFuture.allOf(fileUpload).join();
        }

        // Step 4: Add Message
        CompletableFuture<String> addMessage = thread.addMessage("user", message);
        CompletableFuture.allOf(addMessage).join();

        // Step 5: Run Thread
        CompletableFuture<String> runThread = thread.run();
        CompletableFuture.allOf(runThread).join();
        // System.out.println(runThread.get());

        // Step 6: Get Response
        String response = thread.getRequest();
        return CompletableFuture.completedFuture(response);
    }

    @Async
    public CompletableFuture<String> registerResponse(String messageRegister) throws Exception {
        return processRequest(messageRegister, Register.INSTRUCTIONS, register, registerOpenAiThread, false);
    }

    @Async
    public CompletableFuture<String> ExtractorResponse(String messageExtractor) throws Exception {
        return processRequest(messageExtractor, Extractor.INSTRUCTIONS, extractor, extractorOpenAiThread, true);
    }

    @Async
    public CompletableFuture<String> extractorResearcherResponse(String researcherExtractor) throws Exception {
        return processRequest(researcherExtractor, ExtractorResearcher.INSTRUCTIONS, extractorResearcher,
                extractorResearcherOpenAiThread, false);
    }

    @Async
    public CompletableFuture<String> reviewerResponse(String reviewerMessage) throws Exception {
        return processRequest(reviewerMessage, ReviewerResearcher.INSTRUCTIONS, reviewerResearcher,
                reviewerResearcherThread, false);
    }

    @Async
    public CompletableFuture<String> reviewerRanking(String reviewerRankingmessage) throws Exception {
        return processRequest(reviewerRankingmessage, ReviewerRanking.INSTRUCTIONS, reviewerRanking,
                reviewerRankingThread,
                false);

    }

    @Async
    public CompletableFuture<String> rankingAgent(String rankingAgentmessage) throws Exception {
        return processRequest(rankingAgentmessage, RankingAgent.INSTRUCTIONS, rankingAgent, rankingAgentThread,
                false);

    }

    @Async // I must change the methods name
    public CompletableFuture<String> correctExtracrorResearcherResponse(String extractorResearcherMessage)
            throws Exception {
        extractorResearcherOpenAiThread.addMessage("assistant", extractorResearcherMessage);
        extractorResearcherOpenAiThread.run();
        String response = extractorResearcherOpenAiThread.getRequest();
        return CompletableFuture.completedFuture(response);

    }

    
    @Async
    public CompletableFuture<Void> processReviewerResearcherAgent(String initialMessage) throws Exception {
        CompletableFuture<String> agentResponse = reviewerResponse(initialMessage);
        CompletableFuture.allOf(agentResponse).join();

        String response = agentResponse.get();
        int count = 0;

        while (count < 5) {
            if (!response.contains("---- NO CHANGES REQUIRED, ANALYSIS GOOD ----")) {
                System.out.println("CORRECTIONS REQUIRED BY THE REVIEWER");
                String correctionMessage = agentResponse.get();
                CompletableFuture<String> correctionResponse = extractorResearcherResponse(correctionMessage);
                CompletableFuture.allOf(correctionResponse).join();

                String newMessage = "The corrected response is: " + correctionResponse.get();
                agentResponse = reviewerResponse(newMessage);
                CompletableFuture.allOf(agentResponse).join();

                response = agentResponse.get();
                count++;
                System.out.println(response);
            } else {
                System.out.println("NO FURTHER CORRECTIONS REQUIRED");
                break;
            }
        }

        return CompletableFuture.completedFuture(null);
    }

    @Async
    public CompletableFuture<Void> processReviewerRankingAgent(String initialMessage) throws Exception {
        CompletableFuture<String> agentResponse = reviewerRanking(initialMessage);
        CompletableFuture.allOf(agentResponse).join();

        String response = agentResponse.get();
        int count = 0;

        while (count < 5) {
            if (!response.contains("---- NO CHANGES REQUIRED, ANALYSIS GOOD ----")) {
                System.out.println("CORRECTIONS REQUIRED BY THE REVIEWER RANKING");
                String correctionMessage = agentResponse.get();
                CompletableFuture<String> correctionResponse = rankingAgent(correctionMessage);
                CompletableFuture.allOf(correctionResponse).join();

                String newMessage = "The corrected response is: " + correctionResponse.get();
                agentResponse = reviewerRanking(newMessage);
                CompletableFuture.allOf(agentResponse).join();

                response = agentResponse.get();
                count++;
                System.out.println(response);
            } else {
                System.out.println("NO FURTHER CORRECTIONS REQUIRED");
                break;
            }
        }

        return CompletableFuture.completedFuture(null);
    }

}
