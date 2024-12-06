package com.example.demo.database;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ResearcherRepository extends JpaRepository<ResearcherResult, Long> {
}
