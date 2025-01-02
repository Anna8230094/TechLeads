
package com.example.demo.openai.service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.database.researcher.ResearcherService;
import com.example.demo.database.user.Users;
import com.example.demo.database.user.UsersService;
import com.example.demo.openai.agents.Extractor;
import com.example.demo.openai.agents.ExtractorResearcher;
import com.example.demo.openai.agents.OpenAiAssistant;
import com.example.demo.openai.agents.RankingAgent;
import com.example.demo.openai.agents.Register;
import com.example.demo.openai.agents.ReviewerRanking;
import com.example.demo.openai.agents.ReviewerResearcher;
import com.example.demo.openai.threads.ExtractorThread;
import com.example.demo.openai.threads.OpenAiThread;

/**
 * This class represents my class in Java.
 * @author Aggeliki Despoina Megalou
 * @version 1.0
 */

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

    @Autowired
    UsersService usersService;

    @Autowired
    ResearcherService researcherService;



    // this method is called from controller class after the for submit
    @Async
    public CompletableFuture<Void> startRankingProcess(List<MultipartFile> files, Users user) throws Exception {

        // Step 1:create register
        String messageRegiser = "Here are the details provided by the user:\nfield:" + user.getField() +
                "\nhard skills:" + user.getHardSkills() +
                "\nsoft skills:" + user.getSoftSkills() +
                "\n other tratits:" + user.getOtherTraits();

        CompletableFuture<String> registerResponse = registerResponse(messageRegiser);
        System.out.println("Register Response: " + registerResponse.get());
        CompletableFuture.allOf(registerResponse).join();

        for (MultipartFile file : files) {

            // step 2:create extractor
            String messageExtractor = "The cv that i want you to extract informations is the following: ";
            CompletableFuture<String> extractorResponse = extractorResponse(messageExtractor, file);
            CompletableFuture.allOf(extractorResponse).join();
            System.out.println("Extractor Response: " + extractorResponse.get());

            // step 3:Create extractorResearcher
            String extractorResearcherMessage = "The resume in csv is:" + extractorResponse.get()
                    + "/n The job position in csv is:" + registerResponse.get();
            CompletableFuture<String> extractorResearcherResponse = extractorResearcherResponse(
                    extractorResearcherMessage);
            CompletableFuture.allOf(extractorResearcherResponse).join();
            System.out.println("ExtractorResearcher response is :" + extractorResearcherResponse.get());

            // step 4:Create reviewer reasearcher
            String messageResearcherReviewer = "The response of extraxtorReasearcher is: "
                    + extractorResearcherResponse.get();
            CompletableFuture<String> reviewerExtractorResponse = reviewerExtractorResponse(messageResearcherReviewer);
            CompletableFuture.allOf(reviewerExtractorResponse).join();

            // check if the extractorresearcher gave the willing results
            String reviewerResponse = reviewerExtractorResponse.get();
            if (!reviewerResponse.contains("---- NO CHANGES REQUIRED, ANALYSIS GOOD ----")) {
                extractorResearcherResponse = checkRewierReasearcherResult(messageResearcherReviewer, reviewerResponse);
            }
            ResearcherResult researcherResult = new ResearcherResult();
            researcherResult.setResume(extractorResearcherResponse.get());
            researcherResult.setFileName(file.getOriginalFilename());
            researcherService.saveResearcherResult(researcherResult);
        }

        // step 5:create ranking
        String messageRanking = " The resume from the database are:"; // database whene is ready
        CompletableFuture<String> rankingResponse = rankingAgentResponse(messageRanking);
        CompletableFuture.allOf(rankingResponse).join();

        // step 6: Create reviewer ranking
        String messageReviewerRanking = "The response of RankingAgent is:" + rankingResponse.get();
        CompletableFuture<String> reviewerRankingResponse = reviewerRankingResponse(messageReviewerRanking);
        CompletableFuture.allOf(reviewerRankingResponse).join();

        // Check if the ReviewerRanking gave the willing results
        String responseOfReviewerRanking = reviewerRankingResponse.get();
        if (!responseOfReviewerRanking.contains("---- NO CHANGES REQUIRED, ANALYSIS GOOD ----")) {
            rankingResponse = checkRankingReviewerResult(messageReviewerRanking, responseOfReviewerRanking);

        }
        // insert data into researcher :extractorResearcherResponse in DB researcher
        // the ranking gets data from researcher database
        return CompletableFuture.completedFuture(null);
    }

    // executing the procedure of creating an assistant until getting assistants
    // response
    @Async
    public CompletableFuture<String> processRequest(String message, String instructions, OpenAiAssistant assistant,
            OpenAiThread thread, boolean uploadFile, MultipartFile file) throws Exception {

        // Step 1: Create AI Assistant
        CompletableFuture<String> createAssistant = assistant.createAiAssistant();
        CompletableFuture.allOf(createAssistant).join();

        // Step 2: Create Thread
        CompletableFuture<String> createThread = thread.createThread(instructions, assistant.getAssistantId());
        CompletableFuture.allOf(createThread).join();

        // Step 3: Optional File Upload
        // the upload file must have an argument with the cv path or file
        if (uploadFile) {
            System.out.println("mmmm");
            CompletableFuture<String> fileUpload = extractorOpenAiThread.uploadFile(file);
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
        return processRequest(messageRegister, Register.INSTRUCTIONS, register, registerOpenAiThread, false, null);
    }

    @Async
    public CompletableFuture<String> extractorResponse(String messageExtractor, MultipartFile file) throws Exception {
        return processRequest(messageExtractor, Extractor.INSTRUCTIONS, extractor, extractorOpenAiThread, true, file);
    }

    @Async
    public CompletableFuture<String> extractorResearcherResponse(String researcherExtractor) throws Exception {
        return processRequest(researcherExtractor, ExtractorResearcher.INSTRUCTIONS, extractorResearcher,
                extractorResearcherOpenAiThread, false, null);
    }

    @Async
    public CompletableFuture<String> reviewerExtractorResponse(String reviewerMessage) throws Exception {
        return processRequest(reviewerMessage, ReviewerResearcher.INSTRUCTIONS, reviewerResearcher,
                reviewerResearcherThread, false, null);
    }

    @Async
    public CompletableFuture<String> reviewerRankingResponse(String reviewerRankingmessage) throws Exception {
        return processRequest(reviewerRankingmessage, ReviewerRanking.INSTRUCTIONS, reviewerRanking,
                reviewerRankingThread,
                false, null);

    }

    @Async
    public CompletableFuture<String> rankingAgentResponse(String rankingAgentmessage) throws Exception {
        return processRequest(rankingAgentmessage, RankingAgent.INSTRUCTIONS, rankingAgent, rankingAgentThread,
                false, null);

    }

    // We need the assistant to give instruction to another assistant in order to
    // get a more efficient response
    @Async
    public CompletableFuture<String> correctExtracrorResearcherResponse(String extractorResearcherMessage)
            throws Exception {
        // we keep the same thread id
        extractorResearcherOpenAiThread.addMessage("assistant", extractorResearcherMessage);
        extractorResearcherOpenAiThread.run();
        String response = extractorResearcherOpenAiThread.getRequest();
        return CompletableFuture.completedFuture(response);
    }

    @Async
    public CompletableFuture<String> correctRankingAgentResponse(String rankingAgentMessage)
            throws Exception {
        // we keep the same thread id
        reviewerRankingThread.addMessage("assistant", rankingAgentMessage);
        reviewerRankingThread.run();
        String response = reviewerRankingThread.getRequest();
        return CompletableFuture.completedFuture(response);

    }

    // Check if the result of Extractor Reasearcher is the willing
    @Async
    public CompletableFuture<String> checkRewierReasearcherResult(String extractorReasercherResult,
            String reviewerResponse) throws Exception {

        // executing check max 5 times else accept the last answer
        String finalResponse = null;
        int count = 0;
        do {
            System.out.println("THE REVIEWER SUGGEST CORRECTIONS");
            CompletableFuture<String> extarctorResearcherCorrections = correctExtracrorResearcherResponse(
                    reviewerResponse);
            CompletableFuture.allOf(extarctorResearcherCorrections).join();

            extractorReasercherResult = "The response of extraxtorReasearcher after corrections is: "
                    + extarctorResearcherCorrections.get();
            CompletableFuture<String> reviewerExtractorResponse = reviewerExtractorResponse(extractorReasercherResult);
            CompletableFuture.allOf(reviewerExtractorResponse).join();

            reviewerResponse = reviewerExtractorResponse.get();
            finalResponse = extarctorResearcherCorrections.get();
            count++;
            System.out.println(reviewerResponse.toString());
        } while (count < 5 && !reviewerResponse.contains("---- NO CHANGES REQUIRED, ANALYSIS GOOD ----"));

        return CompletableFuture.completedFuture(finalResponse);
    }

    @Async
    public CompletableFuture<String> checkRankingReviewerResult(String rankingAgentResult,
            String reviewerResponse) throws Exception {
        // executing check max 5 times else accept the last answer
        String finalResponse = null;
        int count = 0;
        do {
            System.out.println("THE REVIEWERRANKING SUGGEST CORRECTIONS");
            CompletableFuture<String> rankingAgentCorrections = correctRankingAgentResponse(reviewerResponse);
            CompletableFuture.allOf(rankingAgentCorrections).join();

            rankingAgentResult = "The response of RankingAgent after corrections is: "
                    + rankingAgentCorrections.get();
            CompletableFuture<String> reviewerRankingResponse = reviewerRankingResponse(rankingAgentResult);
            CompletableFuture.allOf(reviewerRankingResponse).join();

            reviewerResponse = reviewerRankingResponse.get();
            finalResponse = rankingAgentCorrections.get();
            count++;
            System.out.println(reviewerResponse.toString());

        } while (count < 5 && !reviewerResponse.contains("---- NO CHANGES REQUIRED, ANALYSIS GOOD ----"));
        return CompletableFuture.completedFuture(finalResponse);

    }

}