package com.akropoliprishtine.crawler;

import com.akropoliprishtine.entities.DailyJob;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class SchedulersWakeUp {
    private final RestTemplate restTemplate;

    public SchedulersWakeUp(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Scheduled(cron = "0 50 9 * * *")
    public void runScheduler() {
        List<DailyJob> jobs = new ArrayList<>();

        log.info("Scheduler runs to wake up ");

        getPostsPlainJSON();
    }

    public void getPostsPlainJSON() {
        String url = "https://jobs-facebook.herokuapp.com/postemails";
        this.restTemplate.getForObject(url, JsonNode.class);
    }
}
