/**
 * This class represents my class in Java.
 * 
 * <p>It is designed to demonstrate how to declare the author of a class
 * using a JavaDoc comment.</p>
 * 
 * @author Konstantia Stergiou
 * @version 1.0
 */

package com.example.demo.database.researcher;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface ResearcherRepository extends JpaRepository<ResearcherResult, Long> {
    @Query(value = "select * from researcher_result h where h.session_id = :session_id", nativeQuery = true)
    List<ResearcherResult> findBySessionId(@Param("session_id") String session_id);
}
