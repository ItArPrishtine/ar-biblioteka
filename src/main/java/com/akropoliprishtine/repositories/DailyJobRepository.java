package com.akropoliprishtine.repositories;

import com.akropoliprishtine.entities.DailyJob;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DailyJobRepository extends JpaRepository<DailyJob, Long> {
}
