package com.akropoliprishtine.crawler;

import com.akropoliprishtine.entities.ApplicationUser;
import com.akropoliprishtine.entities.DailyJob;
import com.akropoliprishtine.services.ApplicationUserService;
import com.akropoliprishtine.services.EmailService;
import com.fasterxml.jackson.databind.node.ArrayNode;
import lombok.extern.slf4j.Slf4j;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Slf4j
@Service
public class Scheduler {

    @Autowired
    EmailService emailService;

    @Autowired
    ApplicationUserService userService;

    private final RestTemplate restTemplate;

    public Scheduler(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

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
        this.sendEmailsToPeople(jobs);
    }

    public void sendEmailsToPeople(List<DailyJob> jobs) {
        List<ApplicationUser> users = this.userService.getUsersWithAllowedEmail();

        for(int i = 0 ; i < users.size(); i++) {
            String professionalLabels = users.get(i).getProfessionalLabels();
            List<DailyJob> jobsToSend = new ArrayList<>();

            for (int j = 0 ; j < jobs.size(); j++) {
                boolean hasCommonElements = sharesAnElement(
                        jobs.get(j).getTitle().toLowerCase().split(" "),
                        professionalLabels.toLowerCase().split(","));

                if (hasCommonElements) {
                    jobsToSend.add(jobs.get(j));
                }
            }

            this.emailService.sendEmailForJobsPersonally(jobsToSend, users.get(i).getEmail());
        }
    }

    public JsonNode getPostsPlainJSON() {
        String url = "http://jobs-facebook.herokuapp.com/postemails";
        return this.restTemplate.getForObject(url, JsonNode.class);
    }

    private boolean sharesAnElement(String[] a, String[] b) {
        Set<String> bSet = new HashSet<>(Arrays.asList(b));

        for (String str : a) {
            if (bSet.contains(str)) {
                return true;
            }
        }

        return false;
    }
}
