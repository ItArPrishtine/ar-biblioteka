package com.akropoliprishtine.crawler;

import com.akropoliprishtine.entities.DailyJob;
import com.microsoft.playwright.*;

import java.util.ArrayList;
import java.util.List;

public class Kastori implements ICrawler{

    public List<DailyJob> crawlData() {
        List<DailyJob> dailyJobs = new ArrayList<DailyJob>();

        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch((new BrowserType.LaunchOptions().setChromiumSandbox(false)));
            Page page = browser.newPage();
            page.navigate("https://kastori.net/");
            page.waitForLoadState();

            List<ElementHandle> lists = page.querySelectorAll(".listing-item listing-item__jobs");

            lists.forEach(job -> {
                String jobTitle = null;
                String jobLink = null;

                ElementHandle title = job.querySelector(".listing-item__title");
                ElementHandle position = job.querySelector("a");

                if (title != null) {
                    jobTitle = title.innerText();
                }

                if (position != null) {
                    jobLink = position.getAttribute("href").toString();
                }

                if (jobTitle == null || jobLink == null) {
                    return;
                }

                DailyJob dailyJob = new DailyJob();
                dailyJob.setLink(jobLink);
                dailyJob.setTitle(jobTitle);

                dailyJobs.add(dailyJob);
            });

        }

        return dailyJobs;
    }
}
