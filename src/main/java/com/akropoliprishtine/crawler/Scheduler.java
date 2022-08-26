package com.akropoliprishtine.crawler;

import com.akropoliprishtine.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class Scheduler {

    @Autowired
    EmailService emailService;
//    sSXxWw2
    @Scheduled(cron = "0 04 09 * * *")
    public void runScheduler() {
        DayOfWeek dayOfWeek = LocalDate.now().getDayOfWeek();
        List<JobType> jobs = new ArrayList<>();

        switch (dayOfWeek) {
            case MONDAY:
                Telegrafi telegrafi = new Telegrafi();
                jobs = telegrafi.crawlData();
                break;
            case TUESDAY:
                Kastori kastori = new Kastori();
                jobs = kastori.crawlData();
                break;
            case WEDNESDAY:
                OfertaPune ofertaPune = new OfertaPune();
                jobs = ofertaPune.crawlData();
                break;
            case THURSDAY:
                Karriera karriera = new Karriera();
                jobs = karriera.crawlData();
                break;
            case FRIDAY:
                Kastori kastori1 = new Kastori();
                jobs = kastori1.crawlData();
                break;
            case SATURDAY:
                OfertaPune ofertaPune2 = new OfertaPune();
                jobs = ofertaPune2.crawlData();
                break;
            case SUNDAY:
                OfertaPune ofertaPune3 = new OfertaPune();
                jobs = ofertaPune3.crawlData();
                break;
        }

        emailService.sendEmailToPostJobs(null, jobs);
    }
}
