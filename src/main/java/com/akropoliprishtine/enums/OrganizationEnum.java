package com.akropoliprishtine.enums;

public enum OrganizationEnum {
    PRISHTINE("prishtine"),
    TIRANE("tirane"),
    SHKUP("shkup"),
    VLORE("vlore");


    public final String label;

    private OrganizationEnum(String label) {
        this.label = label;
    }
}



