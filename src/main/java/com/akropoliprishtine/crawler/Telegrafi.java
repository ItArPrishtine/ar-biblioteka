package com.akropoliprishtine.crawler;

import com.microsoft.playwright.*;

import java.util.ArrayList;
import java.util.List;

public class Telegrafi implements ICrawler {

    public List<JobType> crawlData() {
        List<JobType> jobTypes = new ArrayList<JobType>();

        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch((new BrowserType.LaunchOptions().setChromiumSandbox(false)));
            Page page = browser.newPage();
            page.navigate("https://jobs.telegrafi.com/");
            page.waitForLoadState();

            List<ElementHandle> lists = page.querySelectorAll(".job-info");
            System.out.println("asdfasdf" + lists.size());

            lists.forEach(job -> {
                String jobTitle = null;
                String jobLink = null;

                ElementHandle title = job.querySelector(".job-name h3");
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

                JobType jobType = new JobType();
                jobType.setLink(jobLink);
                jobType.setTitle(jobTitle);

                jobTypes.add(jobType);
            });

        }
        return jobTypes;
    }
}
