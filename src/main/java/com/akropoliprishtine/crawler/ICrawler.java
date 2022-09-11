package com.akropoliprishtine.crawler;

import com.akropoliprishtine.entities.DailyJob;

import java.util.List;

public interface ICrawler {
    public List<DailyJob> crawlData();
}
