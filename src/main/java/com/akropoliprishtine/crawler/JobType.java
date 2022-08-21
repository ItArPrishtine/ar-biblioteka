package com.akropoliprishtine.crawler;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JobType {
    String title;
    String link;

    @Override
    public String toString() {
        return "Title: " + title + " " + ", link: " + link;
    }
}

