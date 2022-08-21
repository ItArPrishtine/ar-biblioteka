package com.akropoliprishtine.crawler;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.ElementHandle;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

import java.util.ArrayList;
import java.util.List;

public class Kastori implements ICrawler{

    public List<JobType> crawlData() {
        List<JobType> jobTypes = new ArrayList<JobType>();

        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.webkit().launch();
            Page page = browser.newPage();
            page.navigate("https://kastori.net/");
            page.waitForLoadState();

            List<ElementHandle> lists = page.querySelectorAll(".wpjb-type-pune");

            lists.forEach(job -> {
                String jobTitle = null;
                String jobLink = null;

                ElementHandle title = job.querySelector(".wpjb-job_title");
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
