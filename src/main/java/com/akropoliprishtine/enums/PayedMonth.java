package com.akropoliprishtine.enums;

public enum PayedMonth {
    JANUARY("JANAR"),
    FEBRUARY("SHKURT"),
    MARCH("MARS"),
    APRIL("PRILL"),
    MAY("MAJ"),
    JUNE("QERSHOR"),
    JULY("KORRIK"),
    AUGUST("GUSHT"),
    SEPTEMBER("SHTATOR"),
    OCTOBER("TETOR"),
    NOVEMBER("NENTOR"),
    DECEMBER("DHJETOR");

    public final String label;

    private PayedMonth(String label) {
        this.label = label;
    }
}

