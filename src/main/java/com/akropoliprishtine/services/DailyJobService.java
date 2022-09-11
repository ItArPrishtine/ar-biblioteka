package com.akropoliprishtine.services;

import com.akropoliprishtine.entities.DailyJob;
import com.akropoliprishtine.repositories.DailyJobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DailyJobService {
    @Autowired
    DailyJobRepository jobRepository;

    public DailyJob save(DailyJob job) {
        return jobRepository.save(job);
    }

    public List<DailyJob> saveAll(List<DailyJob> jobs) {
        return jobRepository.saveAll(jobs);
    }

    public void deleteAll() {
        jobRepository.deleteAll();
    }
}
