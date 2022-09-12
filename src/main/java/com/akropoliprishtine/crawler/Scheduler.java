package com.akropoliprishtine.crawler;

import com.akropoliprishtine.entities.DailyJob;
import com.akropoliprishtine.services.EmailService;
import com.fasterxml.jackson.databind.node.ArrayNode;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class Scheduler {

    @Autowired
    EmailService emailService;

    private final RestTemplate restTemplate;

    public Scheduler(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Scheduled(cron = "0 10 20 * * *")
    public void runScheduler() {
        List<DailyJob> jobs = new ArrayList<>();

        log.info("Scheduler runs");

        JsonNode json = getPostsPlainJSON();

        ArrayNode arrayNode = (ArrayNode) json.get("data");

        for (int i = 0 ; i < arrayNode.size(); i++) {
            String title = arrayNode.get(i).get("title").asText();
            String link = arrayNode.get(i).get("link").asText();

            DailyJob dailyJob = new DailyJob(title, link);
            jobs.add(dailyJob);

        }

        emailService.sendEmailToPostJobs(jobs);
    }

    public JsonNode getPostsPlainJSON() {
        String url = "https://jobs-facebook.herokuapp.com/postemails";
        return this.restTemplate.getForObject(url, JsonNode.class);
    }
}
