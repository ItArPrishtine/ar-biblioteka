package com.akropoliprishtine.crawler;

import com.akropoliprishtine.entities.DailyJob;
import com.akropoliprishtine.services.DailyJobService;
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

    @Autowired
    DailyJobService dailyJobService;

    @Scheduled(cron = "0 10 22 * * *")
    public void runScheduler() {
        DayOfWeek dayOfWeek = LocalDate.now().getDayOfWeek();
        List<DailyJob> jobs = new ArrayList<>();

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
        }



//        emailService.sendEmailToPostJobs(null, jobs);
        dailyJobService.saveAll(jobs);
    }
}
