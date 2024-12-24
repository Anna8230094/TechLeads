
package com.example.demo.database.rankingresult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service

public class RankingService {

    @Autowired
    private RankingRepository RankingRepository;

    public RankingResult saveRankingResult(RankingResult rankingResult) {

        return RankingRepository.save(rankingResult);

    }

}