package com.akropoliprishtine.enums;

public enum PaymentType {
    MUJORE("MUJORE"),
    DHURIM("DHURIM"),
    MARKETING("MARKETING"),
    BARI("BARI"),
    SHPENZIM("SHPENZIM");

    public final String label;

    private PaymentType(String label) {
        this.label = label;
    }
}

