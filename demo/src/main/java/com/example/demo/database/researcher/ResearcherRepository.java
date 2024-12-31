package com.example.demo.database.researcher;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ResearcherRepository extends JpaRepository<ResearcherResult, Long> {
}
