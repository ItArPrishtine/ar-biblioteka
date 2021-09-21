package com.akropoliprishtine.enums;

public enum PayedYear {
    TWENTYTWENTY("2020"),
    TWENTYTWENTYONE("2021"),
    TWENTYTWENTYTWO("2022");

    public final String label;

    private PayedYear(String label) {
        this.label = label;
    }
}

