
package com.example.demo.database.ranking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service

public class RankingService {

    @Autowired
    private static RankingRepository RankingRepository;
    
        public static RankingResult saveRankingResult(RankingResult rankingResult) {
    
            return RankingRepository.save(rankingResult);

    }

}