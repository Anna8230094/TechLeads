/**
 * This class represents my class in Java.
 * 
 * <p>It is designed to demonstrate how to declare the author of a class
 * using a JavaDoc comment.</p>
 * 
 * @author Konstantia Stergiou
 * @version 1.0
 */

package com.example.demo.database.ranking;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class RankingService {

    @Autowired
    private RankingRepository rankingRepository;

    @Async
    public CompletableFuture<Void> saveRankingResult(RankingResult rankingResult) {
        rankingRepository.save(rankingResult);
        System.out.println("The ranking cv context is saved");
        return CompletableFuture.completedFuture(null);

    }
    public List<RankingResult> getAllranking(RankingResult rankingResult) {
        return rankingRepository.findAll();
    }


}