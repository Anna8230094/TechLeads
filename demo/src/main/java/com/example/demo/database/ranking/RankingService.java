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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RankingService {

    @Autowired
    private RankingRepository rankingRepository;

    public void saveRankingResult(RankingResult rankingResult) {
        rankingRepository.save(rankingResult);
        System.out.println("The ranking cv context is saved");
    }

    public List<RankingResult> getAllranking() {
        return rankingRepository.findAll();
    }
}