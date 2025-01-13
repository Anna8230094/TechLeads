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

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface RankingRepository extends JpaRepository<RankingResult, Long> {
    @Query(value = "select * from ranking_result h where h.session_id = :session_id order by id_ranking asc", nativeQuery = true)
    List<RankingResult> findBySessionId(@Param("session_id") String session_id);
}
