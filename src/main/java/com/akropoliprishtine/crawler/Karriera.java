package com.akropoliprishtine.crawler;

import com.akropoliprishtine.entities.DailyJob;
import com.microsoft.playwright.*;

import java.util.ArrayList;
import java.util.List;

public class Karriera implements ICrawler {

    public List<DailyJob> crawlData() {
        List<DailyJob> dailyJobs = new ArrayList();

        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch((new BrowserType.LaunchOptions().setChromiumSandbox(false)));
            Page page = browser.newPage();
            page.navigate("https://karriera.al/al/result");
            page.waitForLoadState();

            List<ElementHandle> lists = page.querySelectorAll("tbody tr");

            lists.forEach(job -> {
                String jobTitle = null;
                String jobLink = null;

                ElementHandle title = job.querySelector("h4");
                ElementHandle position = job.querySelector("a");

                if (title != null) {
                    jobTitle = title.innerText();
                }

                if (position != null) {
                    jobLink = "https://karriera.al/"  +  position.getAttribute("href").toString();
                }

                if (jobTitle == null || jobLink == null) {
                    return;
                }

                DailyJob dailyJob = new DailyJob();
                dailyJob.setLink(jobLink);
                dailyJob.setTitle(jobTitle);

                System.out.println(dailyJob.toString());

                dailyJobs.add(dailyJob);
            });

        }


        return dailyJobs;
    }
}
