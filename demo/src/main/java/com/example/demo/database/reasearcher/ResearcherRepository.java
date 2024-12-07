package com.example.demo.database.reasearcher;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ResearcherRepository extends JpaRepository<ResearcherResult, Long> {
}
